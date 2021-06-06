package com.example.futuresomething.data

import com.example.futuresomething.data.entity.TmdbResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
interface TmdbApi {
    @GET("3/movie/popular")
    fun getFilms(
            @Query("api_key") apiKey: String,
            @Query("language") language: String,
            @Query("page") page: Int
    ):Call<TmdbResults>
}