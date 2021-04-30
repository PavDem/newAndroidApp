package com.example.futuresomething.domain

import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.versionedparcelable.ParcelField
import com.bumptech.glide.Glide
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.film_item.view.*

@Parcelize
data class Film(
    val id: Int,
    val title: String,
    val poster: Int,
    val description: String,
    var rating: Float = 0f,
    var isInFavorites: Boolean = false
) : Parcelable {
    companion object {
        @JvmStatic
        @BindingAdapter("imagePoster")
        fun loadImage(view: ImageView, resource: Int) {
            Glide.with(view)
                .load(resource)
                .centerCrop()
                .into(view)
        }
    }
}