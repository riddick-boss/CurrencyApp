package abandonedstudio.app.currencyinfo.model.remote.exchangerate.fake

import abandonedstudio.app.currencyinfo.model.remote.exchangerate.ExchangeMainRepository
import abandonedstudio.app.currencyinfo.model.remote.exchangerate.dto.ErrorResponse
import abandonedstudio.app.currencyinfo.model.remote.exchangerate.dto.ExchangeResponse
import abandonedstudio.app.currencyinfo.model.remote.exchangerate.dto.Rates
import abandonedstudio.app.currencyinfo.model.remote.util.Resource
import javax.inject.Inject

class ExchangeFakeRepoImpl @Inject constructor(): ExchangeMainRepository {
    override suspend fun getExchangeRate(date: String?): Resource<ExchangeResponse> {
        val rates = Rates(1.34f, 4.43f, 5.54f, 7.43f, 3.3f)
        return Resource.Success(
            ExchangeResponse(
                true,
                3434,
                "EUR",
                date ?: "2022-12-07",
                rates,
                ErrorResponse(102, "Error msg")
            )
        )
    }
}