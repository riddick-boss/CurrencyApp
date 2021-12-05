package abandonedstudio.app.currencyinfo.ui.exchangeratelist

import abandonedstudio.app.currencyinfo.databinding.ExchangeRateItemBinding
import abandonedstudio.app.currencyinfo.util.Converter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RatesRVAdapter : RecyclerView.Adapter<RatesRVAdapter.RatesViewHolder>() {

    private var data = linkedMapOf<String, Int>()

    inner class RatesViewHolder(val binding: ExchangeRateItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun submitData(list: LinkedHashMap<String, Int>) {
        data = list
//        TODO: change notify method
        notifyDataSetChanged()
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
    }

    override fun getItemCount(): Int {
        return data.size
    }


}