package com.example.futuresomething.activities

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.example.futuresomething.Film
import com.example.futuresomething.R
import com.example.futuresomething.databinding.ActivityMainBinding
import com.example.futuresomething.fragments.*
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private var backPressed = 0L
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavigation()
        initFragment()
    }

    private fun initFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_placeholder, HomeFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun initNavigation() {

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    val tag = "home"
                    val fragment = checkFragmentExistence(tag)
                    //В первом параметре, если фрагмент не найден и метод вернул null, то с помощью
                    //элвиса мы вызываем создание нового фрагмента
                    changeFragment( fragment?: HomeFragment(), tag)
                    true
                }
                R.id.favorites -> {
                    val tag = "favorites"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment( fragment?: FavoritesFragment(), tag)
                    true
                }
                R.id.watch_later -> {
                    val tag = "watch_later"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment( fragment?: WatchLaterFragment(), tag)
                    true
                }
                R.id.selections -> {
                    val tag = "selections"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment( fragment?: SelectionsFragment(), tag)
                    true
                }
                else -> false
            }
        }
    }

    private fun checkFragmentExistence(tag: String): Fragment? = supportFragmentManager.findFragmentByTag(tag)

    private fun changeFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment, tag)
            .addToBackStack(null)
            .commit()
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

    /*private fun iniSplashScreen() {
        val animatedVectorDrawable = AnimatedVectorDrawableCompat.create(this, R.drawable.anim_vector)
        //Устанавливаем animatedVectorDrawable в наше view
        lottie_anim.setImageDrawable(animatedVectorDrawable)
        //Добавляем коллбэк на конец анимации, чтобы опять её запустить
        animatedVectorDrawable?.registerAnimationCallback(object :
            Animatable2Compat.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                lottie_anim.post {
                    animatedVectorDrawable.start()
                }
            }
        })
//Стартуем анимацию
        animatedVectorDrawable?.start()
    }*/

    companion object {
        const val TIME_INTERVAL = 2000
    }
}