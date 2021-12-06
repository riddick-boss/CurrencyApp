package abandonedstudio.app.currencyinfo.ui.exchangeratelist

import abandonedstudio.app.currencyinfo.model.remote.exchangerate.ExchangeMainRepository
import abandonedstudio.app.currencyinfo.model.remote.exchangerate.dto.ExchangeResponse
import abandonedstudio.app.currencyinfo.model.remote.util.Resource
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.LinkedHashMap

@HiltViewModel
class ExchangeRateListViewModel @Inject constructor(private val exchangeMainRepository: ExchangeMainRepository) :
    ViewModel() {

//    TODO: probably use init to fetch first 10 days

    private val _ratesList =
        MutableStateFlow(LinkedHashMap<String, LinkedHashMap<String, Float>>())
    val ratesList = _ratesList.asStateFlow()

    val ld = MutableLiveData<LinkedHashMap<String, LinkedHashMap<String, Float>>>()

    private val _errorMsg = MutableStateFlow("")
    val errorMsg: StateFlow<String> = _errorMsg

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    var date: String = dateFormat.format(Calendar.getInstance().time)

    private fun getExchangeRate(date: String? = null) {
        viewModelScope.launch {
            when (val resource = exchangeMainRepository.getExchangeRate(date)) {
                is Resource.Success -> {
                    if (resource.data != null) {
                        if (resource.data.success) {
                            Log.d("rvs", "Success")
                            val (day, currencies) = convertResponse(resource.data)
                            Log.d("rvs", "About to emit")
                            ld.postValue(linkedMapOf(day to currencies))
                            Log.d("rvs", "Emitted")
                        } else {
                            emitError("Response fail")
                        }
                    } else {
                        emitError("Response data null")
                    }
                }
                is Resource.Error -> {
                    Log.d("remote", resource.errorMessage ?: "Unknown error occurred")
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

    fun getExchangeRateToday() {
        getExchangeRate()
    }

    private fun getExchangeRateFromDate(date: String) {
        getExchangeRate(date)
    }

    fun getExchangeRateFromPreviousDate() {
        setPreviousDay()
        getExchangeRateFromDate(date)
    }

    private fun setPreviousDay() {
        val calendar = Calendar.getInstance()
        calendar.time = dateFormat.parse(date)!!
        calendar.add(Calendar.DATE, -1)
        date = dateFormat.format(calendar.time)
        Log.d("rvs", "prev day: $date")
    }

    private fun emitError(errorMsg: String) {
        _errorMsg.value = errorMsg
    }

}
