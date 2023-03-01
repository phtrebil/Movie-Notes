package com.example.movienotes.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.example.movienotes.R
import com.example.movienotes.databinding.ActivityDetalhesBinding
import com.example.movienotes.model.Movies

class DetalhesActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetalhesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        intent.getParcelableExtra<Movies>("filme")?.let{ filmeCarregado ->
            preencheCampos(filmeCarregado)
        } ?: finish()
    }

    private fun preencheCampos(filmeCarregado: Movies) {
        binding.tituloDoFilme.text = filmeCarregado.title
        binding.dataLancamento.text = filmeCarregado.release_date
        binding.posterFilme.load(filmeCarregado.poster_path){
            fallback(R.drawable.erro)
            error(R.drawable.erro)
        }
        binding.resumo.text = filmeCarregado.overview
        binding.nota.text = filmeCarregado.vote_average.toString()
    }

}
