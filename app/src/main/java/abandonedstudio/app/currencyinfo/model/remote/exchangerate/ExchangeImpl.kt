package abandonedstudio.app.currencyinfo.model.remote.exchangerate

import abandonedstudio.app.currencyinfo.model.remote.exchangerate.dto.ExchangeRate
import io.ktor.client.*
import javax.inject.Inject

class ExchangeImpl @Inject constructor(private val client: HttpClient) : Exchange {

    override suspend fun getExchangeRates(): List<ExchangeRate> {
        TODO("Not yet implemented")
    }

}
