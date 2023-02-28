package com.example.movienotes.model

class RespostaMovieApi (
    val page: Int,
    val results : List<Movies>,
    val total_pages: Int,
    val total_results: Int
    ){
}