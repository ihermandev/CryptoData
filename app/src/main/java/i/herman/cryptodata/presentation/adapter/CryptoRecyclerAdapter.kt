package i.herman.cryptodata.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import i.herman.cryptodata.R
import i.herman.cryptodata.data.db.entity.CryptoModel
import i.herman.cryptodata.utils.downloadImage

/**
 * Created by Illia Herman on 18.05.2021.
 */
class CryptoRecyclerAdapter(
    private val data: List<CryptoModel>,
    private val listener: ((CryptoModel) -> Unit)?
) : RecyclerView.Adapter<CryptoRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_crypto, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        with(holder) {
            cryptoName.text = item.coin_name
            cryptoAcronym.text = item.acronym
            cryptoLogo.downloadImage(item.logo)
            bindListener(item)
        }
    }

    override fun getItemCount(): Int = data.size

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
}
