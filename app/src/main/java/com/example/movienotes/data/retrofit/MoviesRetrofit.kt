package com.example.movienotes.data.retrofit

import android.content.Context
import android.widget.Toast
import com.example.movienotes.listeners.OnSerachApiListener
import com.example.movienotes.model.RespostaMovieApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

class MoviesRetrofit(
    val context: Context
) {


    val resposta = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val despensaService = resposta.create(BuscaMovies::class.java)

    fun buscaFilmes(listener: OnSerachApiListener, movie_nome: String?) {
        val call: Call<RespostaMovieApi> = despensaService.buscaMovies(movie_nome)
        call.enqueue(object : Callback<RespostaMovieApi> {
            override fun onResponse(
                call: Call<RespostaMovieApi>,
                response: Response<RespostaMovieApi>
            ) {
                if (!response.isSuccessful) {
                    Toast.makeText(context, "Filme não localizado", Toast.LENGTH_SHORT).show()
                    return
                }
                listener.onResponse(response.body())
            }

            override fun onFailure(call: Call<RespostaMovieApi>, t: Throwable) {
                listener.OnError(t.message)
            }
        })
    }
}

interface BuscaMovies {
    @GET("3/search/movie")
    fun buscaMovies(
        @Query("q") movie_nome: String?,
        @Query("api_key") apiKey: String = "c78edd48fe96ba6a9e5d60d02baeb679"
    )
    : Call<RespostaMovieApi>
}
