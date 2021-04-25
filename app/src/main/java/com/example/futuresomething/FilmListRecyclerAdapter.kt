package com.example.futuresomething

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.futuresomething.databinding.FilmItemBinding

class FilmListRecyclerAdapter(private val clickListener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class FilmViewHolder(var binding: FilmItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val items = mutableListOf<Film>()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FilmViewHolder(
            DataBindingUtil.inflate(inflater, R.layout.film_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //Проверяем какой у нас ViewHolder
        when (holder) {
            is FilmViewHolder -> {
                //привязываем dataBinding
                holder.binding.film = items[position]
                //Обрабатываем нажатие на весь элемент целиком(можно сделать на отдельный элемент
                //напрмер, картинку) и вызываем метод нашего листенера, который мы получаем из
                //конструктора адаптера
                holder.binding.itemContainer.setOnClickListener {
                    clickListener.click(items[position])
                }
            }
        }
    }

    fun addItems(newItems: List<Film>) {
        //val diffCallback = FilmDiffCallback(items, newFilms)
        //val diffResult = DiffUtil.calculateDiff(diffCallback)
        items.clear()
        items.addAll(newItems)
        //calculating wrong size, in need of fix
        //diffResult.dispatchUpdatesTo(this)
        //temporal workaround
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun click(film: Film)
    }

    //class FilmViewHolder(var binding: FilmItemBinding): RecyclerView.ViewHolder(binding.root)
}
