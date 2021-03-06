package com.example.futuresomething.fragments

import android.os.Bundle
import android.transition.*
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.futuresomething.*
import com.example.futuresomething.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.merge_home_screen_content.*
import kotlinx.android.synthetic.main.merge_home_screen_content.main_recycler
import kotlinx.android.synthetic.main.merge_home_screen_content.search_view

import java.util.*


class HomeFragment : Fragment() {
    private var justStarted = true

    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    private val filmsDataBase = listOf(
        Film(
            0,
            "Willy Wonka",
            R.drawable.willy_wonka,
            "Willy Wonka is extraordinary. He's a chocolate-making genius who relishes nonsense. He can't abide ugliness in factories. And he likes to make mischief, even if that means talking to the President of the United States whilst pretending to be a man from Mars, as he does in Charlie and the Great Glass Elevator.",
            5.7f
        ),
        Film(1, "JOKER", R.drawable.joker, "This should be a description", 8.7f),
        Film(3, "INTERSTELLAR", R.drawable.interstellar, "This should be a description", 4.5f),
        Film(4, "The Thing", R.drawable.the_thing, "This should be a description", 6.9f),
        Film(5, "The Godfather", R.drawable.the_godfather, "This should be a description", 8.5f),
        Film(6, "Akira", R.drawable.akira, "This should be a description", 6f),
        Film(
            7,
            "The Shawshank Redemption",
            R.drawable.the_shawshank_redemption,
            "This should be a description",
            5.4f
        ),
        Film(8, "Deerskin", R.drawable.deerskin, "This should be a description", 3.5f)

    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AnimationHelper.performFragmentCircularRevealAnimation(
            home_fragment_root,
            requireActivity(),
            1
        )

        initRecycler()

        search_view.setOnClickListener {
            search_view.isIconified = false
        }

        //Подключаем слушателя изменений введенного текста в поиска
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            //Этот метод отрабатывает при нажатии кнопки "поиск" на софт клавиатуре
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            //Этот метод отрабатывает на каждое изменения текста
            override fun onQueryTextChange(newText: String): Boolean {
                //Если ввод пуст то вставляем в адаптер всю БД
                if (newText.isEmpty()) {
                    filmsAdapter.addItems(filmsDataBase)
                    return true
                }
                //Фильтруем список на поискк подходящих сочетаний
                val result = filmsDataBase.filter {
                    //Чтобы все работало правильно, нужно и запрос, и имя фильма приводить к нижнему регистру
                    it.title.toLowerCase(Locale.getDefault())
                        .contains(newText.toLowerCase(Locale.getDefault()))
                }
                //Добавляем в адаптер
                filmsAdapter.addItems(result)
                return true
            }
        })
    }


    fun initRecycler() {
        //находим наш RV
        main_recycler.apply {
            //Инициализируем наш адаптер в конструктор передаем анонимно инициализированный интерфейс,
            //оставим его пока пустым, он нам понадобится во второй части задания
            filmsAdapter =
                FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                    override fun click(film: Film) {
                        (requireActivity() as MainActivity).launchDetailsFragment(film)
                    }
                })
            //Присваиваем адаптер
            adapter = filmsAdapter
            //Присвои layoutmanager
            layoutManager = LinearLayoutManager(requireContext())
            //Применяем декоратор для отступов
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
        //Кладем нашу БД в RV
        filmsAdapter.addItems(filmsDataBase)
    }

}