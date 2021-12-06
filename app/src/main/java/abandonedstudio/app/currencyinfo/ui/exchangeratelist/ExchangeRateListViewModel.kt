package abandonedstudio.app.currencyinfo.ui.exchangeratelist

import abandonedstudio.app.currencyinfo.model.remote.exchangerate.ExchangeMainRepository
import abandonedstudio.app.currencyinfo.model.remote.exchangerate.dto.ExchangeResponse
import abandonedstudio.app.currencyinfo.model.remote.util.Resource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExchangeRateListViewModel @Inject constructor(private val exchangeMainRepository: ExchangeMainRepository) :
    ViewModel() {

//    TODO: probably use init to fetch first 10 days

    private val _ratesList =
        MutableStateFlow(LinkedHashMap<String, LinkedHashMap<String, Float>>())
    val ratesList: StateFlow<LinkedHashMap<String, LinkedHashMap<String, Float>>> = _ratesList

    private val _errorMsg = MutableStateFlow("")
    val errorMsg: StateFlow<String> = _errorMsg

    private fun getExchangeRate(date: String? = null) {
        viewModelScope.launch {
            when (val resource = exchangeMainRepository.getExchangeRate(date)) {
                is Resource.Success -> {
                    if (resource.data != null) {
                        if (resource.data.success) {
                            val (day, currencies) = convertResponse(resource.data)
                            _ratesList.value[day] = currencies
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

    fun getExchangeRateToday() {
        getExchangeRate()
    }

    fun getExchangeRateFromDate(date: String) {
        getExchangeRate(date)
    }

    private fun emitError(errorMsg: String) {
        _errorMsg.value = errorMsg
    }

}
