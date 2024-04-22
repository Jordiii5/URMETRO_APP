package com.example.urmetro.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.RectF
import com.example.urmetro.R

class PowerUp(context: Context, screenX: Int, screenY: Int):ScreenElement {
    override var bitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.powerup)
    override val width = 200
    override val height = 200
    override var positionX = (width..(screenX-width)).random()
    override var positionY = (height..(screenY-height)).random()
    override var speed = 0
    override var hitbox = RectF()

    init{
        bitmap = Bitmap.createScaledBitmap(bitmap, width, height,false)
        hitbox.top = positionY.toFloat()+25
        hitbox.bottom = hitbox.top+height-50
        hitbox.left = positionX.toFloat()+25
        hitbox.right = hitbox.left+width-50
    }

    override fun updateElement(){

    }
}