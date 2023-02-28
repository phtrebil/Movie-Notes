package com.example.movienotes.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movienotes.databinding.ItemListaDeFilmesBinding
import com.example.movienotes.model.Movies

class ListaDeFilmesAdapter(

    private val context: Context,
    filmes: List<Movies> = emptyList(),
    var onItemClick: (movies: Movies) -> Unit = {}

) : RecyclerView.Adapter<ListaDeFilmesAdapter.ViewHolder>() {

    private val filmes = filmes.toMutableList()

    inner class ViewHolder(private val binding: ItemListaDeFilmesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var filme: Movies

        fun vincula(filme: Movies) {
            this.filme = filme
            binding.tituloItem.text = filme.title
            binding.anoDoSilme.text = filme.release_date
            binding.imagemItem.load(filme.poster_path)
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filme = filmes[position]
        holder.vincula(filme)
    }

    override fun getItemCount(): Int = filmes.size

    fun atualiza(filmes: List<Movies>){
        this.filmes.clear()
        this.filmes.addAll(filmes)
        notifyDataSetChanged()
    }


}