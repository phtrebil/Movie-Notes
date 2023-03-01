package com.example.movienotes.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movienotes.data.retrofit.MoviesRetrofit
import com.example.movienotes.databinding.ActivityListaDeFilmesBinding
import com.example.movienotes.listeners.OnSerachApiListener
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
        val retrofit = MoviesRetrofit(this)
        configuraBusca(retrofit)
        configuraRecyclerView()

    }

    private fun configuraRecyclerView() {
        binding.rvListaDeFilmes.adapter = adapter
        binding.rvListaDeFilmes.layoutManager = GridLayoutManager(this, 2)
        adapter.onItemClick = {
            val intent = Intent(
                this,
                DetalhesActivity::class.java
            )
                .apply {
                    putExtra("filme", it)
                }
            startActivity(intent)
        }
    }

    private fun configuraBusca(retrofit: MoviesRetrofit) {
        binding.busca.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(filmeBuscado: String?): Boolean {
                retrofit.buscaFilmes(listener, filmeBuscado)
                return true
            }
        })
    }

    val listener = object : OnSerachApiListener {
        override fun onResponse(resposta: RespostaMovieApi?) {
            if (resposta == null) {
                Toast.makeText(baseContext, "Resposta nula", Toast.LENGTH_SHORT).show()
                return
            }
            resposta?.let { adapter.atualiza(it.results) }
            Toast.makeText(baseContext, "Resposta não nula", Toast.LENGTH_SHORT).show()
        }

        override fun OnError(mensagem: String?) {
            Toast.makeText(
                baseContext,
                "um erro aconteceu",
                Toast.LENGTH_SHORT
            )
                .show()
        }

    }

}