package abandonedstudio.app.currencyinfo.ui.exchangeratelist

import abandonedstudio.app.currencyinfo.model.remote.exchangerate.Exchange
import abandonedstudio.app.currencyinfo.model.remote.exchangerate.dto.ExchangeRate
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExchangeRateListViewModel @Inject constructor(private val exchange: Exchange) : ViewModel() {

    suspend fun getExchangeRates(): List<ExchangeRate> {
        return exchange.getExchangeRates()
    }

}
