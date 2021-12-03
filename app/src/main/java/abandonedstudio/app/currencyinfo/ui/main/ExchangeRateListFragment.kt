package abandonedstudio.app.currencyinfo.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import abandonedstudio.app.currencyinfo.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExchangeRateListFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.exchange_rate_list, container, false)
    }



}