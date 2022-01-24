package es.santirivera.pruebamarvel.util

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadUrl(placeholder: Int, url: String) {
        Glide.with(context).load(url).placeholder(placeholder).into(this)
}