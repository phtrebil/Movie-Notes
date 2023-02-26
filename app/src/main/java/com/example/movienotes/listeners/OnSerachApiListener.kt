package com.example.movienotes.listeners

import com.example.movienotes.model.Movies

interface OnSerachApiListener {
    fun onResponse(resposta: List<Movies>?)
    fun OnError(mensagem: String?)
}