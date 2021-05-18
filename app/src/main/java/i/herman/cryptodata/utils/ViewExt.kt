package i.herman.cryptodata.utils

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

/**
 * Created by Illia Herman on 18.05.2021.
 */
fun AppCompatImageView.downloadImage(url: String) {
    Glide.with(context)
        .load(url)
        .centerInside()
        .into(this)
}