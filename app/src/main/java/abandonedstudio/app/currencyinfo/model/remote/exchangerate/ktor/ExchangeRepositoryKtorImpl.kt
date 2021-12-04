package abandonedstudio.app.currencyinfo.model.remote.exchangerate.ktor

import abandonedstudio.app.currencyinfo.model.remote.exchangerate.ExchangeMainRepository
import abandonedstudio.app.currencyinfo.model.remote.exchangerate.dto.ExchangeResponse
import abandonedstudio.app.currencyinfo.model.remote.util.Resource
import io.ktor.client.*
import javax.inject.Inject

class ExchangeRepositoryKtorImpl @Inject constructor(private val client: HttpClient) : ExchangeMainRepository {

    override suspend fun getExchangeRate(date: String?): Resource<ExchangeResponse> {
        TODO("Not yet implemented")
    }
}
