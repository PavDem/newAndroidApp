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
        val title: String,
        val poster: String, //У нас будет приходить ссылка на картинку, так что теперь это String
        val description: String,
        var rating: Double = 0.0, //Приходит не целое число с API
        var isInFavorites: Boolean = false
) : Parcelable