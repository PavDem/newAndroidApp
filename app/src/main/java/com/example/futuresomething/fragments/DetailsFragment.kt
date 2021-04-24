package com.example.futuresomething.fragments

import android.content.Intent
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.futuresomething.AnimationHelper
import com.example.futuresomething.Film
import com.example.futuresomething.PseudoFavoritesDataBase
import com.example.futuresomething.R
import com.example.futuresomething.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    private lateinit var film: Film
    private lateinit var binding: FragmentDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFilmsDetails()

        binding.detailsFabFavorites.setOnClickListener {
            if (!film.isInFavorites) {
                PseudoFavoritesDataBase.data
                binding.detailsFabFavorites.setImageResource(R.drawable.baseline_favorite_white_24dp)
                PseudoFavoritesDataBase.data.addOrRemove(film)
                film.isInFavorites = true
            } else {
                binding.detailsFabFavorites.setImageResource(R.drawable.baseline_favorite_border_black_24dp)
                PseudoFavoritesDataBase.data.addOrRemove(film)
                film.isInFavorites = false
            }
        }

        binding.detailsFabShare.setOnClickListener {
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
        binding.detailsToolbar.title = film.title
        //Устанавливаем картинку
        binding.detailsPoster.setImageResource(film.poster)
        //Устанавливаем описание
        binding.detailsDescription.text = film.description

        film.isInFavorites = PseudoFavoritesDataBase.data.isFilmInFavorites(film)
        binding.detailsFabFavorites.setImageResource(
            if (film.isInFavorites) R.drawable.baseline_favorite_white_24dp
            else R.drawable.baseline_favorite_border_black_24dp
        )
    }
}