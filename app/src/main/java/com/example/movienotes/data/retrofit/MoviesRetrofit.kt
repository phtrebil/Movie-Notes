package com.example.movienotes.data.retrofit

import com.example.movienotes.model.Movies
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

class MoviesRetrofit {

    val resposta = Retrofit.Builder()
        .baseUrl("https://mdblist.p.rapidapi.com/?i=tt0073195")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val despensaService = resposta.create(BuscaMovies::class.java)
}

interface BuscaMovies{
    @Headers(
        "Accept: application/json",
        "X-RapidAPI-Key: b473f84f12mshf183cf88d4c2d1cp122916jsn5c71e4b91f03",
        "X-RapidAPI-Host: mdblist.p.rapidapi.com"
    )
    @GET("?s={nome_movie}")
    fun buscaTodas(
        @Path("nome_movie") movie_nome: String
    ): Call<List<Movies>>
}
