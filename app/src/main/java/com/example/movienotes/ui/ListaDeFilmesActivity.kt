package com.example.movienotes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movienotes.R
import com.example.movienotes.databinding.ActivityListaDeFilmesBinding
import com.example.movienotes.ui.adapter.ListaDeFilmesAdapter

class ListaDeFilmesActivity : AppCompatActivity() {

    val adapter = ListaDeFilmesAdapter(this)
    val binding by lazy {
        ActivityListaDeFilmesBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        configuraRecyclerView()
    }

    private fun configuraRecyclerView() {
        val recyclerView = binding.rvListaDeFilmes
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 2)
    }
}