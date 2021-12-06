package abandonedstudio.app.currencyinfo.ui.exchangerate

import abandonedstudio.app.currencyinfo.databinding.ExchangeRateBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExchangeRateFragment : Fragment() {

    private val viewModel: ExchangeRateViewModel by viewModels()
    private var _binding: ExchangeRateBinding? = null
    private val binding get() = _binding!!

    private val args: ExchangeRateFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ExchangeRateBinding.inflate(inflater, container, false)
        viewModel.getDataFromArgs(args.day, args.rate, args.rateCurrency)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dayTV.text = viewModel.day
        binding.rateTV.text = viewModel.rate
        binding.rateCurrencyTV.text = viewModel.rateCurrency
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
