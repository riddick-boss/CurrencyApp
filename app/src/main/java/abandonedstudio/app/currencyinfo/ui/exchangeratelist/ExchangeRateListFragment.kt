package abandonedstudio.app.currencyinfo.ui.exchangeratelist

import abandonedstudio.app.currencyinfo.databinding.ExchangeRateListBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ExchangeRateListFragment : Fragment() {

    private val viewModel: ExchangeRateListViewModel by viewModels()
    private var _binding: ExchangeRateListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ExchangeRateListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        showing toast indicating api response error
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.errorMsg.collectLatest {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.ratesList.collectLatest {
//                TODO: set RV with this list
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}