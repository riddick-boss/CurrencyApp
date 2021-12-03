package abandonedstudio.app.currencyinfo.di

import abandonedstudio.app.currencyinfo.model.remote.exchangerate.Exchange
import abandonedstudio.app.currencyinfo.model.remote.exchangerate.ExchangeImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    fun provideHttpClient() = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    @Provides
    fun provideExchange(exchangeImpl: ExchangeImpl): Exchange = exchangeImpl

}
