package com.example.futuresomething

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initButtons()
    }

    fun initButtons() {
        button_menu.setOnClickListener {
            Toast.makeText(this, R.string.menu, Toast.LENGTH_SHORT).show()
        }

        button_favorites.setOnClickListener {
            Toast.makeText(this, R.string.favorites, Toast.LENGTH_SHORT).show()
        }

        button_view_later.setOnClickListener {
            Toast.makeText(this, R.string.view_later, Toast.LENGTH_SHORT).show()
        }

        button_compilation.setOnClickListener {
            Toast.makeText(this, R.string.compilation, Toast.LENGTH_SHORT).show()
        }

        button_settings.setOnClickListener {
            Toast.makeText(this, R.string.settings, Toast.LENGTH_SHORT).show()
        }
    }
}