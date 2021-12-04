package abandonedstudio.app.currencyinfo.model.remote.exchangerate.retrofit

import abandonedstudio.app.currencyinfo.BuildConfig
import abandonedstudio.app.currencyinfo.model.remote.exchangerate.dto.ExchangeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ExchangeApiRetrofit {

    @GET("latest")
    suspend fun getExchangeRatesToday(
        @Query("access_key") apiKey: String = BuildConfig.FIXER_API_KEY
    ): Response<ExchangeResponse>

    @GET("date")
    suspend fun getExchangeRatesFromDate(
        @Path( value = "date", encoded = true) date: String,
        @Query("access_key") apiKey: String = BuildConfig.FIXER_API_KEY
    ): Response<ExchangeResponse>

}