package abandonedstudio.app.currencyinfo.ui.exchangeratelist

import abandonedstudio.app.currencyinfo.model.remote.HttpRoutes
import abandonedstudio.app.currencyinfo.model.remote.exchangerate.dto.ExchangeResponse
import abandonedstudio.app.currencyinfo.model.remote.exchangerate.dto.Rates
import abandonedstudio.app.currencyinfo.model.remote.exchangerate.retrofit.ExchangeApiRetrofit
import abandonedstudio.app.currencyinfo.model.remote.exchangerate.retrofit.ExchangeRetrofitRepositoryImpl
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(AndroidJUnit4::class)
class ExchangeRateListViewModelTest {

    lateinit var viewModel: ExchangeRateListViewModel

    @Before
    fun setUp() {
        viewModel = ExchangeRateListViewModel(
            ExchangeRetrofitRepositoryImpl(
                Retrofit.Builder()
                    .baseUrl(HttpRoutes.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ExchangeApiRetrofit::class.java)
            )
        )
    }

//    @Test
//    fun convertResponseCorrectOutput() {
//        val rates = Rates(1.34f, 4.43f, 5.54f, 7.43f, 3.3f)
//        val response = ExchangeResponse(true, 124124, "EUR", "06-12-2021", rates)
//        val (day, currencies) = viewModel.convertResponse(response)
//        val map = linkedMapOf<String, Float>()
//        map["AUD"] = 4.43f
//        map["CAD"] = 5.54f
//        map["MXN"] = 3.3f
//        map["PLN"] = 7.43f
//        map["USD"] = 1.34f
//        Truth.assertThat(day).isEqualTo("06-12-2021")
//        Truth.assertThat(currencies).isEqualTo(map)
//    }

}
