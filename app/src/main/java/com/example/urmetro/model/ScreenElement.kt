package com.example.urmetro.model

import android.graphics.Bitmap
import android.graphics.RectF

interface ScreenElement {
    val bitmap: Bitmap
    val width:Int
    val height:Int
    var positionX:Int
    var positionY:Int
    var speed:Int
    var hitbox: RectF

    fun updateElement()
}