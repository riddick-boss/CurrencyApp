package abandonedstudio.app.currencyinfo.model.remote.exchangerate.retrofit

import abandonedstudio.app.currencyinfo.model.remote.HttpRoutes
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking

import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ExchangeRetrofitRepositoryImplTest {

    private val exchangeRetrofitRepositoryImpl = ExchangeRetrofitRepositoryImpl(
        Retrofit.Builder()
            .baseUrl(HttpRoutes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ExchangeApiRetrofit::class.java)
    )

    @Before
    fun setUp() {
    }

    @Test
    fun getExchangeRateTodayCorrectResult(): Unit = runBlocking {
        val resource = exchangeRetrofitRepositoryImpl.getExchangeRate()
        resource.data.let {
            if (it != null) {
                Truth.assertThat(it.success).isTrue()
                Truth.assertThat(it.base).isEqualTo("EUR")
            }
        }
    }

    @Test
    fun getExchangeRateFromDateCorrectResult() : Unit = runBlocking {
        val resource =  exchangeRetrofitRepositoryImpl.getExchangeRate("2013-03-16")
        resource.data.let {
            if (it != null) {
                Truth.assertThat(it.success).isTrue()
                Truth.assertThat(it.base).isEqualTo("EUR")
                Truth.assertThat(it.rates.USD).isEqualTo(1.307716)
                Truth.assertThat(it.rates.AUD).isEqualTo(1.256333)
                Truth.assertThat(it.rates.CAD).isEqualTo(1.333812)
                Truth.assertThat(it.rates.PLN).isEqualTo(4.150819)
                Truth.assertThat(it.rates.MXN).isEqualTo(16.259128)
            }
        }
    }


    @After
    fun tearDown() {
    }
}