package com.example.urmetro.model

import kotlinx.serialization.Serializable

@Serializable
data class Usuari (
    val usuari_id: Int = 0,
    val usuari_nom: String,
    val usuari_dni: String,
    val usuari_adreça: String,
    val usuari_telefon: Int,
    val usuari_contacte_emergencia: Int,
    val usuari_imatge: String,
    val usuari_contra: String
)