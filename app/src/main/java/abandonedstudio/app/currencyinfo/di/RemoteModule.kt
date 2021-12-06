package abandonedstudio.app.currencyinfo.di

import abandonedstudio.app.currencyinfo.model.remote.HttpRoutes
import abandonedstudio.app.currencyinfo.model.remote.exchangerate.ExchangeMainRepository
import abandonedstudio.app.currencyinfo.model.remote.exchangerate.retrofit.ExchangeApiRetrofit
import abandonedstudio.app.currencyinfo.model.remote.exchangerate.retrofit.ExchangeRetrofitRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun provideKtorHttpClient() = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    @Singleton
    @Provides
    fun provideExchangeApiRetrofit(): ExchangeApiRetrofit = Retrofit.Builder()
        .baseUrl(HttpRoutes.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ExchangeApiRetrofit::class.java)

    @Singleton
    @Provides
    fun provideExchangeApi(exchangeImpl: ExchangeRetrofitRepositoryImpl): ExchangeMainRepository = exchangeImpl

}
