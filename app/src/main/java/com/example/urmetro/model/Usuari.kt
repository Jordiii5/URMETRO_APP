package com.example.urmetro.model

data class Usuari (
    val usuari_id: Int,
    val usuari_nom: String,
    val usuari_dni: String,
    val usuari_telefon: Int,
    val usuari_contacte_emergencia: Int,
    val usuari_contra: String
)