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
        MutableStateFlow(emptyList<ExchangeResponse>() as MutableList<ExchangeResponse>)
    val ratesList: StateFlow<List<ExchangeResponse>> = _ratesList

    private val _errorMsg = MutableStateFlow("")
    val errorMsg: StateFlow<String> = _errorMsg

    private fun getExchangeRate(date: String? = null) {
        viewModelScope.launch {
            when (val resource = exchangeMainRepository.getExchangeRate(date)) {
                is Resource.Success -> {
                    if (resource.data != null) {
                        if (resource.data.success) {
                            _ratesList.value.add(resource.data)
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
