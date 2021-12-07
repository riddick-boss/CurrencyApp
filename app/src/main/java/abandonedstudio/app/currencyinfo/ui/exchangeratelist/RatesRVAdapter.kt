package abandonedstudio.app.currencyinfo.ui.exchangeratelist

import abandonedstudio.app.currencyinfo.databinding.ExchangeRateItemBinding
import abandonedstudio.app.currencyinfo.util.Converter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RatesRVAdapter(private val parentDay: String, private val listener: OnItemClickedRatesRV) :
    RecyclerView.Adapter<RatesRVAdapter.RatesViewHolder>() {

    private var data = linkedMapOf<String, Float>()

    inner class RatesViewHolder(val binding: ExchangeRateItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun submitData(list: LinkedHashMap<String, Float>) {
        data = list
//        notifyDataSetChanged()
        notifyItemInserted(data.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ExchangeRateItemBinding.inflate(inflater)
        return RatesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RatesViewHolder, position: Int) {
        val (currentCurrency, currentRateValue) = Converter.getElementFromLinkedHashMapByIndex(
            data,
            holder.absoluteAdapterPosition
        )
        holder.binding.currencyTV.text = currentCurrency
        holder.binding.rateValueTV.text = currentRateValue.toString()
        holder.binding.rateItemCV.setOnClickListener {
            listener.navigate(parentDay, currentRateValue.toString(), currentCurrency)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface OnItemClickedRatesRV {
        fun navigate(day: String, rate: String, rateCurrency: String)
    }

}
