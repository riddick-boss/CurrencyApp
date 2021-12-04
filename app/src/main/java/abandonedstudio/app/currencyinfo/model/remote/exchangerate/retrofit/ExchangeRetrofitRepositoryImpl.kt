package abandonedstudio.app.currencyinfo.model.remote.exchangerate.retrofit

import abandonedstudio.app.currencyinfo.model.remote.exchangerate.ExchangeMainRepository
import abandonedstudio.app.currencyinfo.model.remote.exchangerate.dto.ExchangeResponse
import abandonedstudio.app.currencyinfo.model.remote.util.Resource
import javax.inject.Inject

class ExchangeRetrofitRepositoryImpl @Inject constructor(private val exchangeApiRetrofit: ExchangeApiRetrofit) :
    ExchangeMainRepository {

    override suspend fun getExchangeRate(date: String?): Resource<ExchangeResponse> {
        return try {
            val response = if (date == null) {
                exchangeApiRetrofit.getExchangeRatesToday()
            } else {
                exchangeApiRetrofit.getExchangeRatesFromDate(date)
            }
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error occurred")
        }
    }

}