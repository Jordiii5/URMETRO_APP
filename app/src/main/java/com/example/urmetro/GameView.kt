package com.example.myvideogame.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.graphics.RectF
import android.media.MediaPlayer
import android.media.SoundPool
import android.view.MotionEvent
import android.view.SurfaceView
import androidx.navigation.findNavController
import com.example.urmetro.JocSpaceFragmentDirections
import com.example.urmetro.R
import com.example.urmetro.model.Enemy
import com.example.urmetro.model.Player
import com.example.urmetro.model.PowerUp
import com.example.urmetro.model.ScreenElement
import com.example.urmetro.model.Shot
import kotlinx.coroutines.*

@SuppressLint("ViewConstructor")
class GameView(context: Context, val size: Point) : SurfaceView(context) {
    var canvas: Canvas = Canvas()
    val paint: Paint = Paint()
    val player = Player(context,size.x,size.y)
    val screenElements = mutableListOf<ScreenElement>()
    var playing = true
    var backroundMusic = MediaPlayer.create(context, R.raw.starwarstheme)
    var enemiesDefeated = 0
    val soundPool: SoundPool = SoundPool.Builder().setMaxStreams(5).build()
    val shotSound = soundPool.load(context, R.raw.shot_sound, 0)
    val enemyDeath = soundPool.load(context, R.raw.explosion_ship, 0)

    init {
        startGame()
        spawnEnemies()
        shoot()
        powerUp()
    }

    fun startGame(){
        CoroutineScope(Dispatchers.Main).launch{
            while(playing){
                backroundMusic.start()
                backroundMusic.setOnCompletionListener {
                    backroundMusic.start()
                }
                draw()
                update()
                delay(10)
            }
            backroundMusic.pause()


            val action = JocSpaceFragmentDirections.actionJocSpaceFragmentToConclusionSpaceJocFragment(enemiesDefeated)
            findNavController().navigate(action)

        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun spawnEnemies() {
        var enemyDelay = 1000L
        var speed = 0
        GlobalScope.launch {
            while (playing) {
                val enemy = Enemy(context,size.x,size.y)
                enemy.speed+=speed
                screenElements.add(enemy)
                delay(enemyDelay)
                if(enemyDelay>200) {
                    enemyDelay -= 25
                }
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun shoot(){
        GlobalScope.launch {
            while (playing) {
                playSound(shotSound)
                screenElements.add(Shot(context,size.x,size.y,player.positionX+(player.width/2).toInt(),player.positionY))
                delay(500)
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun powerUp(){
        GlobalScope.launch {
            while (playing) {
                delay(10000)
                if(screenElements.filter { it is PowerUp }.size == 0) screenElements.add(PowerUp(context,size.x,size.y))
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            player.positionX = event.x.toInt()-(player.width/2).toInt()
            player.positionY = event.y.toInt()-(player.height/2).toInt()
            player.hitbox.top = player.positionY.toFloat()
            player.hitbox.bottom = player.hitbox.top+player.height
            player.hitbox.left = player.positionX.toFloat()
            player.hitbox.right = player.hitbox.left+player.width
        }
        return true
    }

    fun draw(){
        if (holder.surface.isValid) {
            canvas = holder.lockCanvas()
            canvas.drawColor(Color.BLACK)
            canvas.drawBitmap(player.bitmap,player.positionX.toFloat(),player.positionY.toFloat(),paint)
            try{
                for (element in screenElements){
                    if(element is Enemy){
                        var i = 0
                        var dead = false
                        while (i in screenElements.indices&&!dead){
                            if(screenElements[i] is Shot && RectF.intersects(element.hitbox,screenElements[i].hitbox)){
                                screenElements.remove(element)
                                playSound(enemyDeath)
                                enemiesDefeated++
                                dead = true
                            } else {
                                canvas.drawBitmap(element.bitmap,element.positionX.toFloat(),element.positionY.toFloat(),paint)
                            }
                            i++
                        }
                        if(RectF.intersects(element.hitbox,player.hitbox)) playing=false
                    } else if(element is Shot){
                        canvas.drawBitmap(element.bitmap,element.positionX.toFloat(),element.positionY.toFloat(),paint)
                    } else {
                        canvas.drawBitmap(element.bitmap,element.positionX.toFloat(),element.positionY.toFloat(),paint)
                        if(RectF.intersects(element.hitbox,player.hitbox)) {
                            screenElements.remove(element)
                            powerUps()
                        }
                    }
                }
            }catch (_:java.util.ConcurrentModificationException){
            }catch (_:java.lang.IndexOutOfBoundsException){}
            holder.unlockCanvasAndPost(canvas)
        }
    }

    fun powerUps(){
        when((0..1).random()){
            0 -> {
                canvas.drawColor(Color.WHITE)
                val enemies = screenElements.filter { it is Enemy }.size
                enemiesDefeated+=enemies
                screenElements.clear()
            }
            1 -> {
                for(element in screenElements){
                    if(element is Enemy) element.speed = element.speed - (element.speed*2)
                }
            }
        }
    }

    fun update(){
        try{
            for(element in screenElements){
                if(element is Enemy){
                    if(element.positionY<size.y) element.updateElement()
                    else if(element.positionY<=0){
                        screenElements.remove(element)
                        enemiesDefeated++
                    }
                    else playing = false
                } else if(element is Shot) {
                    if(element.positionY>0) element.updateElement()
                    else screenElements.remove(element)
                }
            }
        }catch (_:java.util.ConcurrentModificationException){}
    }

    fun playSound(id: Int){
        soundPool.play(id, 1f, 1f, 0, 0, 1f)
    }
}