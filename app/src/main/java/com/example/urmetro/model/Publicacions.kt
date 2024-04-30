package com.example.urmetro.model

data class Publicacions(
    val publicacio_id: Int,
    val publicacio_foto: String,
    val publicacio_peu_foto: String,
    val publicacio_likes: Int,
    val usuari_id: Int
)