package i.herman.cryptodata.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import i.herman.cryptodata.R
import i.herman.cryptodata.data.db.entity.CryptoModel
import i.herman.cryptodata.utils.downloadImage
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Illia Herman on 18.05.2021.
 */
class CryptoRecyclerAdapter(
    private var data: List<CryptoModel> = mutableListOf(),
    private val listener: ((CryptoModel) -> Unit)?
) : RecyclerView.Adapter<CryptoRecyclerAdapter.ViewHolder>(), Filterable {

    var dataFilterList = mutableListOf<CryptoModel>()

    init {
        dataFilterList = data.toMutableList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_crypto, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataFilterList[position]

        with(holder) {
            cryptoName.text = item.coin_name
            cryptoAcronym.text = item.acronym
            cryptoLogo.downloadImage(item.logo)
            bindListener(item)
        }
    }

    override fun getItemCount(): Int {
        val data = dataFilterList
        return if (data.isNullOrEmpty()) 0 else data.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cryptoName: AppCompatTextView = view.findViewById(R.id.tv_title)
        val cryptoAcronym: AppCompatTextView = view.findViewById(R.id.tv_acronym)
        val cryptoLogo: AppCompatImageView = view.findViewById(R.id.iv_logo)

        fun bindListener(item: CryptoModel) {
            itemView.setOnClickListener {
                listener?.invoke(item)
            }
        }
    }

    fun addItems(data: List<CryptoModel>) {
        this.data = data
        dataFilterList = data.toMutableList()
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                dataFilterList = if (charSearch.isEmpty()) data.toMutableList()
                else {
                    val resultList = ArrayList<CryptoModel>()
                    data.forEach { item ->
                        if (item.coin_name.lowercase()
                                .contains(charSearch.lowercase())
                            || item.acronym.lowercase()
                                .contains(charSearch.lowercase())
                        ) resultList.add(item)
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = dataFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataFilterList = results?.values as MutableList<CryptoModel>
                notifyDataSetChanged()
            }
        }
    }
}
