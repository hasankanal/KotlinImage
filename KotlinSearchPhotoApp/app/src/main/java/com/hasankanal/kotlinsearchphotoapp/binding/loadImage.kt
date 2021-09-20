package com.hasankanal.kotlinsearchphotoapp.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hasankanal.kotlinsearchphotoapp.R
import com.hasankanal.kotlinsearchphotoapp.utils.Constants

@BindingAdapter("android:imageUrl")
fun loadImage(imageView: ImageView, url : String?){
    Glide.with(imageView.context)
        .load(url)
        .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background))
        .into(imageView)
}

