package abandonedstudio.app.currencyinfo.ui.exchangeratelist

import abandonedstudio.app.currencyinfo.databinding.ExchangeRateListItemBinding
import abandonedstudio.app.currencyinfo.util.Converter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DaysRVAdapter(private val childListener: RatesRVAdapter.OnItemClickedRatesRV) :
    RecyclerView.Adapter<DaysRVAdapter.DaysViewHolder>() {

    private var data = linkedMapOf<String, LinkedHashMap<String, Float>>()

    inner class DaysViewHolder(val binding: ExchangeRateListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun submitData(list: LinkedHashMap<String, LinkedHashMap<String, Float>>) {
        data.putAll(list)
//        TODO: change notify method
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ExchangeRateListItemBinding.inflate(inflater)
        return DaysViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DaysViewHolder, position: Int) {
        val (currentDay, currentRatesMap) = Converter.getElementFromLinkedHashMapByIndex(
            data,
            holder.absoluteAdapterPosition
        )
        holder.binding.dayTV.text = currentDay
        val ratesAdapter = RatesRVAdapter(currentDay, childListener)
        holder.binding.ratesRV.apply {
            adapter = ratesAdapter
//            TODO: check if working
            layoutManager = LinearLayoutManager(context)
            ratesAdapter.submitData(currentRatesMap)
        }
//        ratesAdapter.submitData(currentRatesMap)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}