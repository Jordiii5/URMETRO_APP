package com.example.urmetro.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.RectF
import com.example.urmetro.R

class Enemy(context: Context, screenX: Int, screenY: Int):ScreenElement {
    override var bitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.tiefighter)
    override val width = 100
    override val height = 100
    override var positionX = (width..(screenX-width)).random()
    override var positionY = (height..(screenY/4)-height).random()
    override var speed = (1..3).random()
    override var hitbox = RectF()

    init{
        bitmap = Bitmap.createScaledBitmap(bitmap, width, height,false)
    }

    override fun updateElement(){
        positionY+=speed
        hitbox.top = positionY.toFloat()+25
        hitbox.bottom = hitbox.top+height-50
        hitbox.left = positionX.toFloat()+25
        hitbox.right = hitbox.left+width-50
    }
}