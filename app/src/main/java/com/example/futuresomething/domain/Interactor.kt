package com.example.futuresomething.domain

import com.example.futuresomething.data.MainRepository

class Interactor(val repo: MainRepository) {
    fun getFilmsDB(): List<Film> = repo.filmsDataBase
}