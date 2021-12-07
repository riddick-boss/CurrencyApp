package abandonedstudio.app.currencyinfo.ui.exchangeratelist

import abandonedstudio.app.currencyinfo.model.remote.exchangerate.ExchangeMainRepository
import abandonedstudio.app.currencyinfo.model.remote.exchangerate.dto.ExchangeResponse
import abandonedstudio.app.currencyinfo.model.remote.util.Resource
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.LinkedHashMap

@HiltViewModel
class ExchangeRateListViewModel @Inject constructor(private val exchangeMainRepository: ExchangeMainRepository) :
    ViewModel() {

    val ratesListLD = MutableLiveData<LinkedHashMap<String, LinkedHashMap<String, Float>>>()

    val errorMsgLD = MutableLiveData<String>()

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    var date: String = dateFormat.format(Calendar.getInstance().time)

    private fun getExchangeRate(date: String? = null) {
        viewModelScope.launch {
            when (val resource = exchangeMainRepository.getExchangeRate(date)) {
                is Resource.Success -> {
                    if (resource.data != null) {
                        if (resource.data.success) {
                            val (day, currencies) = convertResponse(resource.data)
                            ratesListLD.postValue(linkedMapOf(day to currencies))
                        } else {
                            emitError("Response fail")
                        }
                    } else {
                        emitError("Response data null")
                    }
                }
                is Resource.Error -> {
                    emitError(resource.errorMessage ?: "Unknown error occurred")
                }
            }
        }
    }

    private fun convertResponse(data: ExchangeResponse): Pair<String, LinkedHashMap<String, Float>> {
        val day = data.date
        val currencies = linkedMapOf<String, Float>()
        currencies["AUD"] = data.rates.AUD
        currencies["CAD"] = data.rates.CAD
        currencies["MXN"] = data.rates.MXN
        currencies["PLN"] = data.rates.PLN
        currencies["USD"] = data.rates.USD
        return Pair(day, currencies)
    }

    private fun getExchangeRateToday() {
        getExchangeRate()
    }

    private fun getExchangeRateFromDate(date: String) {
        getExchangeRate(date)
    }

    fun getExchangeRateFromPreviousDate() {
        if (date == dateFormat.format(Calendar.getInstance().time)) {
            getExchangeRateToday()
            setPreviousDay()
            return
        }
        getExchangeRateFromDate(date)
        setPreviousDay()
    }

    private fun setPreviousDay() {
        val calendar = Calendar.getInstance()
        calendar.time = dateFormat.parse(date)!!
        calendar.add(Calendar.DATE, -1)
        date = dateFormat.format(calendar.time)
    }

    private fun emitError(errorMsg: String) {
        errorMsgLD.postValue(errorMsg)
    }

}
