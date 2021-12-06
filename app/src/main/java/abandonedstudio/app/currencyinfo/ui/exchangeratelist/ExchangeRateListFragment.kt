package abandonedstudio.app.currencyinfo.ui.exchangeratelist

import abandonedstudio.app.currencyinfo.databinding.ExchangeRateListBinding
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ExchangeRateListFragment : Fragment(), RatesRVAdapter.OnItemClickedRatesRV {

    private val viewModel: ExchangeRateListViewModel by viewModels()
    private var _binding: ExchangeRateListBinding? = null
    private val binding get() = _binding!!

    private lateinit var daysRVAdapter: DaysRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ExchangeRateListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDaysRV()
        viewModel.getExchangeRateToday()

//        showing toast indicating api response error
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.errorMsg.collectLatest {
                if (it.isNotBlank()){
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.ratesList.collect {
                Toast.makeText(requireContext(), "List submitted", Toast.LENGTH_SHORT).show()
                Log.d("rvs", "in fragment: $it")
                daysRVAdapter.submitData(it)
            }
        }

        binding.loadMoreB.setOnClickListener {
            viewModel.getExchangeRateToday()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupDaysRV() = binding.daysRV.apply {
        daysRVAdapter = DaysRVAdapter(this@ExchangeRateListFragment)
        adapter = daysRVAdapter
        layoutManager = LinearLayoutManager(context)
    }

    //    navigate to ExchangeRateFragment
    override fun navigate(day: String, rate: String, rateCurrency: String) {
        val action =
            ExchangeRateListFragmentDirections.actionExchangeRateListFragmentToExchangeRateFragment(
                day = day,
                rate = rate,
                rateCurrency = rateCurrency
            )
        Navigation.findNavController(requireView()).navigate(action)
    }

}
