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
                Truth.assertThat(it.success).isFalse()
//                Truth.assertThat(it.base).isEqualTo("EUR")
            }

        }
    }


    @After
    fun tearDown() {
    }
}