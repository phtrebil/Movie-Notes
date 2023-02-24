package com.example.movienotes.data.retrofit


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

        "X-RapidAPI-Key: x",
        "X-RapidAPI-Host: x"

    )
    @GET("?s={nome_movie}")
    fun buscaTodas(
        @Path("nome_movie") movie_nome: String
    ): Call<List<Void>>
}
