package abandonedstudio.app.currencyinfo.ui.exchangerate

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExchangeRateViewModel @Inject constructor() : ViewModel() {

    var day: String = "N/A"
    var rate: String = "N/A"
    var rateCurrency: String = "N/A"

    fun getDataFromArgs(day: String, rate: String, rateCurrency: String) {
        this.day = day
        this.rate = rate
        this.rateCurrency = rateCurrency
    }
}