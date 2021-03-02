package com.example.futuresomething

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    val filmsDataBase = listOf(
        Film(0, "Film title", R.drawable.poster5, "This should be a description"),
        Film(1, "Film title", R.drawable.poster6, "This should be a description"),
        Film(3, "Film title", R.drawable.poster7, "This should be a description"),
        Film(4, "Film title", R.drawable.poster8, "This should be a description"),
        Film(5, "Film title", R.drawable.poster9, "This should be a description"),
        Film(6, "Film title", R.drawable.poster10, "This should be a description"),
        Film(7, "Film title", R.drawable.poster11, "This should be a description"),
        Film(8, "Film title", R.drawable.poster12, "This should be a description")

    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNavigation()
        initRecycler()

    }

    fun initRecycler() {
        //находим наш RV
        main_recycler.apply {
            //Инициализируем наш адаптер в конструктор передаем анонимно инициализированный интерфейс,
            //оставим его пока пустым, он нам понадобится во второй части задания
            filmsAdapter =
                FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                    override fun click(film: Film) {
                        //Создаем бандл и кладем туда объект с данными фильма
                        val bundle = Bundle()
                        //Первым параметром указывается ключ, по которому потом будем искать, вторым сам
                        //передаваемы объект
                        bundle.putParcelable("film", film)
                        //Запускаем наше активити
                        val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                        //Прикрепляем бандл к интенту
                        intent.putExtras(bundle)
                        //Запускаем активити через интент
                        startActivity(intent)
                    }
                })
            //Присваиваем адаптер
            adapter = filmsAdapter
            //Присвои layoutmanager
            layoutManager = LinearLayoutManager(this@MainActivity)
            //Применяем декоратор для отступов
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
//Кладем нашу БД в RV
            filmsAdapter.addItems(filmsDataBase)
    }


    fun initNavigation() {
        val snackbarFavorites = Snackbar.make(main_layout, R.string.favorites, Snackbar.LENGTH_SHORT)
        val snackbarViewLater = Snackbar.make(main_layout, R.string.view_later, Snackbar.LENGTH_SHORT)

        topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.settings -> {
                    Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }


        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.favorites -> {
                    snackbarFavorites.show()
                    snackbarFavorites.setAction("Ok") {
                        snackbarFavorites.dismiss()
                    }
                    true
                }
                R.id.watch_later -> {
                    snackbarViewLater.show()
                    snackbarViewLater.setAction("Ok") {
                        snackbarFavorites.dismiss()
                    }
                    true
                }
                R.id.selections -> {
                    Toast.makeText(this, R.string.menu_selections_title, Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
}