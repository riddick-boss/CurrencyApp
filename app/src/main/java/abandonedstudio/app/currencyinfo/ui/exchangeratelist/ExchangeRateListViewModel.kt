package abandonedstudio.app.currencyinfo.ui.exchangeratelist

import abandonedstudio.app.currencyinfo.model.remote.exchangerate.ExchangeMainRepository
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExchangeRateListViewModel @Inject constructor(private val exchangeMainRepository: ExchangeMainRepository) : ViewModel() {


}
