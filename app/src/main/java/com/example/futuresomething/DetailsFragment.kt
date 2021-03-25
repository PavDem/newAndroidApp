package com.example.futuresomething

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {
    private lateinit var film: Film

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFilmsDetails()

        details_fab_favorites.setOnClickListener {
            if (!film.isInFavorites) {
                PseudoFavoritesDataBase.data
                details_fab_favorites.setImageResource(R.drawable.baseline_favorite_white_24dp)
                PseudoFavoritesDataBase.data.addOrRemove(film)
                film.isInFavorites = true
            } else {
                details_fab_favorites.setImageResource(R.drawable.baseline_favorite_border_black_24dp)
                PseudoFavoritesDataBase.data.addOrRemove(film)
                film.isInFavorites = false
            }
        }

        details_fab_share.setOnClickListener {
            //Создаем интент
            val intent = Intent()
            //Указываем action с которым он запускается
            intent.action = Intent.ACTION_SEND
            //Кладем данные о нашем фильме
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Check out this film: ${film.title} \n\n ${film.description}"
            )
            //Указываем MIME тип, чтобы система знала, какое приложения предложить
            intent.type = "text/plain"
            //Запускаем наше активити
            startActivity(Intent.createChooser(intent, "Share To:"))
        }
    }

    private fun setFilmsDetails() {
        //Получаем наш фильм из переданного бандла
        film = arguments?.get("film") as Film


        //Устанавливаем заголовок
        details_toolbar.title = film.title
        //Устанавливаем картинку
        details_poster.setImageResource(film.poster)
        //Устанавливаем описание
        details_description.text = film.description

        film.isInFavorites = PseudoFavoritesDataBase.data.isFilmInFavorites(film)
        details_fab_favorites.setImageResource(
            if (film.isInFavorites) R.drawable.baseline_favorite_white_24dp
            else R.drawable.baseline_favorite_border_black_24dp
        )
    }
}