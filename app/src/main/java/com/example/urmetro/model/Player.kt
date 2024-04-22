package com.example.urmetro.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.RectF
import com.example.urmetro.R

class Player(context: Context, screenX: Int, screenY: Int) {
    var bitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.xwing)
    val width = screenX / 8f
    val height = screenY / 10f
    var positionX = screenX/2
    var positionY = screenY*2/3
    var hitbox = RectF()

    init{
        bitmap = Bitmap.createScaledBitmap(bitmap, width.toInt(), height.toInt(),false)
    }
}