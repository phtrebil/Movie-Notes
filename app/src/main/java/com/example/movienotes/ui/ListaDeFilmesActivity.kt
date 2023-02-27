package com.example.movienotes.ui

import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movienotes.data.retrofit.MoviesRetrofit
import com.example.movienotes.databinding.ActivityListaDeFilmesBinding
import com.example.movienotes.listeners.OnSerachApiListener
import com.example.movienotes.model.Movies
import com.example.movienotes.model.RespostaMovieApi
import com.example.movienotes.ui.adapter.ListaDeFilmesAdapter

class ListaDeFilmesActivity : AppCompatActivity() {

    var adapter = ListaDeFilmesAdapter(this)
    val binding by lazy {
        ActivityListaDeFilmesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.busca.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                MoviesRetrofit(this@ListaDeFilmesActivity)
                    .buscaFilmes(listener,p0)
                return true
            }
        })
    }

    val listener = object : OnSerachApiListener {
        override fun onResponse(resposta: RespostaMovieApi?) {
            if(resposta==null){
                Toast.makeText(this@ListaDeFilmesActivity,
                    "Filme não encontrado",
                    Toast.LENGTH_SHORT)
                    .show()
                return
            }
            mostraResultado(resposta)
        }

        override fun OnError(mensagem: String?) {
            Toast.makeText(this@ListaDeFilmesActivity,
            "um erro aconteceu",
            Toast.LENGTH_SHORT)
                .show()
        }
        
    }

    private fun mostraResultado(resposta: RespostaMovieApi) {
        binding.rvListaDeFilmes.setHasFixedSize(true)
        binding.rvListaDeFilmes.layoutManager = GridLayoutManager(this@ListaDeFilmesActivity, 2)
        adapter = ListaDeFilmesAdapter(this@ListaDeFilmesActivity, resposta.search)
        binding.rvListaDeFilmes.adapter = adapter

    }

}