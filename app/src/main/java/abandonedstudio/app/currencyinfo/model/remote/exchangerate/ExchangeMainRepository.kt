package abandonedstudio.app.currencyinfo.model.remote.exchangerate

import abandonedstudio.app.currencyinfo.model.remote.exchangerate.dto.ExchangeResponse
import abandonedstudio.app.currencyinfo.model.remote.util.Resource

interface ExchangeMainRepository {

    suspend fun getExchangeRate(date: String? = null): Resource<ExchangeResponse>

}
