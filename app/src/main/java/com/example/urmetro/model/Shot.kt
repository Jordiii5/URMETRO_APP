package com.example.urmetro.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.RectF
import com.example.urmetro.R

class Shot(context: Context, screenX: Int, screenY: Int, playerX:Int, playerY: Int):ScreenElement {
    override var bitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.lasershot)
    override val width = 50
    override val height = 50
    override var positionX = playerX-width/2
    override var positionY = playerY
    override var speed = 50
    override var hitbox = RectF()
    init{
        bitmap = Bitmap.createScaledBitmap(bitmap, width, height,false)
    }

    override fun updateElement(){
        positionY -= speed
        hitbox.top = positionY.toFloat()
        hitbox.bottom = hitbox.top+height
        hitbox.left = positionX.toFloat()+10
        hitbox.right = hitbox.left+width-20
    }
}