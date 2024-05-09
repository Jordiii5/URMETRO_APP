package com.example.urmetro.model

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.serialization.Serializable

@RequiresApi(Build.VERSION_CODES.O)
@Serializable
data class Publicacions(
    val publicacio_id: Int,
    val publicacio_foto: String,
    val publicacio_peu_foto: String,
    val publicacio_likes: Int,
    val usuari_id: Int
)