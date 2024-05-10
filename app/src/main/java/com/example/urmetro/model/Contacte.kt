package com.example.urmetro.model

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.serialization.Serializable

@RequiresApi(Build.VERSION_CODES.O)
@Serializable
data class Contacte (
    val contacte_id: Int,
    val contacte_nom: String,
    val contacte_telefon: Int,
    val usuari_id: Int
)