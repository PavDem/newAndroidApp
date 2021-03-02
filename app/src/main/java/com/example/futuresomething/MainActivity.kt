package com.example.futuresomething

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var backPressed = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNavigation()
        iniFragment()
    }

    private fun iniFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_placeholder, HomeFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun initNavigation() {
        val snackbarFavorites = Snackbar.make(main, R.string.favorites, Snackbar.LENGTH_SHORT)
        val snackbarViewLater = Snackbar.make(main, R.string.view_later, Snackbar.LENGTH_SHORT)

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

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            doubleTapToExit()
        } else {
            super.onBackPressed()
        }
    }

    private fun doubleTapToExit() {
        if (backPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            finish()
        } else {
            Toast.makeText(this, "Double tap for exit", Toast.LENGTH_SHORT).show()
        }

        backPressed = System.currentTimeMillis()
    }

    fun launchDetailsFragment(film: Film) {
        //Создаем "посылку"
        val bundle = Bundle()
        //Кладем наш фильм в "посылку"
        bundle.putParcelable("film", film)
        //Кладем фрагмент с деталями в перменную
        val fragment = DetailsFragment()
        //Прикрепляем нашу "посылку" к фрагменту
        fragment.arguments = bundle

        //Запускаем фрагмент
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        const val TIME_INTERVAL = 2000
    }
}