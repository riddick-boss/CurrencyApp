package abandonedstudio.app.currencyinfo.ui.exchangeratelist

import abandonedstudio.app.currencyinfo.databinding.ExchangeRateListBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint

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

//        showing toast indicating api response error
        viewModel.errorMsgLD.observe(viewLifecycleOwner, {
            if (it.isNotBlank()) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.ratesListLD.observe(viewLifecycleOwner, {
            daysRVAdapter.submitData(it)
            viewModel.checkIfEnoughDaysLoaded(it)
        })

        binding.daysRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.getExchangeRateFromPreviousDate()
                }
            }
        })

        if (viewModel.ratesListLD.value.isNullOrEmpty()) {
            viewModel.getExchangeRateFromPreviousDate()
        } else {
            daysRVAdapter.submitData(viewModel.ratesListLD.value!!)
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
    override fun navigate(parentRVPos: Int, day: String, rate: String, rateCurrency: String) {
        val action =
            ExchangeRateListFragmentDirections.actionExchangeRateListFragmentToExchangeRateFragment(
                day = day,
                rate = rate,
                rateCurrency = rateCurrency
            )
        Navigation.findNavController(requireView()).navigate(action)
    }

}
