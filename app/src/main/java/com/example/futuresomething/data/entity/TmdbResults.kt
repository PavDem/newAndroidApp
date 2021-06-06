package com.example.futuresomething.data.entity

import com.example.futuresomething.data.entity.TmdbFilm
import com.google.gson.annotations.SerializedName

data class TmdbResults(
        @SerializedName("page")
        val page: Int,
        @SerializedName("results")
        val tmdbFilms: List<TmdbFilm>,
        @SerializedName("total_pages")
        val totalPages: Int,
        @SerializedName("total_results")
        val totalResults: Int
)