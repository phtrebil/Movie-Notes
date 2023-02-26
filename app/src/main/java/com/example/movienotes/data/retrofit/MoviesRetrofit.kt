package com.example.movienotes.data.retrofit

import android.content.Context
import android.widget.Toast
import com.example.movienotes.listeners.OnSerachApiListener
import com.example.movienotes.model.Movies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

class MoviesRetrofit(
    val context: Context
) {


    val resposta = Retrofit.Builder()
        .baseUrl("https://mdblist.p.rapidapi.com/?i=tt0073195")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val despensaService = resposta.create(BuscaMovies::class.java)

    fun buscaFilmes(listener: OnSerachApiListener, nome_movie: String){
        val call: Call<List<Movies>> = despensaService.buscaTodas(movie_nome = nome_movie)
        call.enqueue(object : Callback<List<Movies>?> {
            override fun onResponse(
                call: Call<List<Movies>?>,
                response: Response<List<Movies>?>
            ) {
                if(!response.isSuccessful){
                    Toast.makeText(context, "Filme não localizado", Toast.LENGTH_SHORT)
                    return
                }
                listener.onResponse(response.body())
            }

            override fun onFailure(call: Call<List<Movies>?>, t: Throwable) {
                listener.OnError(t.message)
            }
        })


    }
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
    ): Call<List<Movies>>
}