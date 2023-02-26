package com.example.movienotes.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movienotes.databinding.ItemListaDeFilmesBinding
import com.example.movienotes.model.Movies

class ListaDeFilmesAdapter(

    val context: Context,
    filmes: List<Movies> = emptyList(),
    var onItemClick: (movies: Movies) -> Unit = {}

) : RecyclerView.Adapter<ListaDeFilmesAdapter.ViewHolder>() {

    private val filmes = filmes.toMutableList()

    inner class ViewHolder(private val binding: ItemListaDeFilmesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var filme: Movies

        fun vincula(filme: Movies) {
            this.filme = filme
            binding.tituloItem.text = filme.titulo
            binding.anoDoSilme.text = filme.ano
            binding.imagemItem.load(filme.imagem)
        }

        init {
            itemView.setOnClickListener{
                onItemClick(filme)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        return ViewHolder(ItemListaDeFilmesBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = filmes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filme = filmes[position]
        holder.vincula(filme)
    }
}