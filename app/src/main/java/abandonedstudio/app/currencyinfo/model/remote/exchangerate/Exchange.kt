package abandonedstudio.app.currencyinfo.model.remote.exchangerate

import abandonedstudio.app.currencyinfo.model.remote.exchangerate.dto.ExchangeRate

interface Exchange {

    suspend fun getExchangeRates() : List<ExchangeRate>

}