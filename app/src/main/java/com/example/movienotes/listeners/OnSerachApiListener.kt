package com.example.movienotes.listeners

import com.example.movienotes.model.RespostaMovieApi

interface OnSerachApiListener {
    fun onResponse(resposta: RespostaMovieApi?)
    fun OnError(mensagem: String?)
}