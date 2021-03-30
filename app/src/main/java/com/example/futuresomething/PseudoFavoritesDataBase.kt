package com.example.futuresomething

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class PseudoFavoritesDataBase private constructor() {
    private val favoriteList = mutableListOf<Film>()

    companion object {
        val data = PseudoFavoritesDataBase()
    }

    fun addOrRemove(film: Film) {
        if (favoriteList.contains(film)) {
            favoriteList.remove(film)
        } else {
            favoriteList.add(film)
        }
    }

    fun isFilmInFavorites(film: Film): Boolean {
        if (favoriteList.contains(film)) return true
        return false
    }
}
