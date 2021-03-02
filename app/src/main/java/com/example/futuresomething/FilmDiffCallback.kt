package com.example.futuresomething

import androidx.recyclerview.widget.DiffUtil

class FilmDiffCallback(val oldList: List<Film>, val newList: List<Film>): DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFilm = oldList[oldItemPosition]
        val newFilm = newList[newItemPosition]
        return oldFilm.title == newFilm.title &&
                oldFilm.description == newFilm.description &&
                oldFilm.poster == newFilm.poster
    }
}