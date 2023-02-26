package com.example.movienotes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movienotes.data.retrofit.MoviesRetrofit
import com.example.movienotes.databinding.ActivityListaDeFilmesBinding
import com.example.movienotes.listeners.OnSerachApiListener
import com.example.movienotes.model.Movies
import com.example.movienotes.ui.adapter.ListaDeFilmesAdapter

class ListaDeFilmesActivity : AppCompatActivity() {

    val retrofit = MoviesRetrofit(this)
    var adapter = ListaDeFilmesAdapter(this)
    val binding by lazy {
        ActivityListaDeFilmesBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.busca.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(nome_filme: String?): Boolean {
                retrofit.buscaFilmes(listener, nome_filme)
                return true
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })


    }

    private val listener = object: OnSerachApiListener{
        override fun onResponse(resposta: List<Movies>?) {
            if(resposta==null){
                Toast.makeText(baseContext, "Filme não localizado", Toast.LENGTH_SHORT)
                return
            }
            mostraResposta(resposta)
        }

        override fun OnError(mensagem: String?) {
            Toast.makeText(baseContext, "Ocorreu um Erro", Toast.LENGTH_SHORT)
        }
    }

    private fun mostraResposta(resposta: List<Movies>) {
        configuraRecyclerView(resposta)
    }

    private fun configuraRecyclerView(resposta: List<Movies>) {
        val recyclerView = binding.rvListaDeFilmes
        recyclerView.setHasFixedSize(true)
        adapter = ListaDeFilmesAdapter(baseContext, resposta)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 2)
    }
}