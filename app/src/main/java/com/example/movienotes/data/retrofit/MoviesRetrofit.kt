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
import retrofit2.http.Path
import retrofit2.http.Query

class MoviesRetrofit(
    val context: Context
) {


    val resposta = Retrofit.Builder()
        .baseUrl("https://mdblist.p.rapidapi.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val despensaService = resposta.create(BuscaMovies::class.java)

    fun buscaFilmes(listener: OnSerachApiListener, movie_nome: String?) {
        val call: Call<RespostaMovieApi> = despensaService.buscaTodas(movie_nome)
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
                Toast.makeText(context, "cheguei aqui", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<RespostaMovieApi>, t: Throwable) {
                listener.OnError(t.message)
            }
        })
    }
}

interface BuscaMovies {
    @Headers(

            "Accept: application/json",
            "X-RapidAPI-Key: b473f84f12mshf183cf88d4c2d1cp122916jsn5c71e4b91f03",
            "X-RapidAPI-Host: https://mdblist.p.rapidapi.com"

    )

    @GET("/")
    fun buscaTodas(@Query("s") movie_nome: String?): Call<RespostaMovieApi>
}
