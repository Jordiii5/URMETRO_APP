package com.example.urmetro

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.urmetro.TematicaTrivialFragment.Companion.tematicaPartida
import com.example.urmetro.databinding.FragmentPlayGameTrivialBinding
import com.example.urmetro.model.preguntas.preguntasDeArte
import com.example.urmetro.model.preguntas.preguntasDeCiencia
import com.example.urmetro.model.preguntas.preguntasDeCine
import com.example.urmetro.model.preguntas.preguntasDeDeportes
import com.example.urmetro.model.preguntas.preguntasDeGeografia
import com.example.urmetro.model.preguntas.preguntasDeHistoria
import com.example.urmetro.model.preguntas.preguntasDeVideojuegos


class PlayGameTrivialFragment : Fragment(), View.OnClickListener {


    lateinit var binding: FragmentPlayGameTrivialBinding

    companion object {
        var score = 0
        var ronda = 0
        private val listaTematicas = mutableListOf(preguntasDeCine, preguntasDeCiencia, preguntasDeHistoria, preguntasDeArte, preguntasDeDeportes, preguntasDeGeografia, preguntasDeVideojuegos)
        var pregunta: List<MutableList<String>> = mutableListOf()
        var respuesta: List<MutableList<String>> = mutableListOf()
        var randomQuestion = listaTematicas.shuffled()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayGameTrivialBinding.inflate(layoutInflater)
        randomQuestion = listaTematicas.shuffled()
        score = 0
        ronda = 0
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pregunta = mutableListOf()
        respuesta = mutableListOf()
        when (tematicaPartida) {
            0 -> {
                pregunta = preguntasDeCine.shuffled()
                binding.background.setImageResource(R.drawable.fondo_cine)
            }
            1 -> {
                pregunta = preguntasDeCiencia.shuffled()
                binding.background.setImageResource(R.drawable.fondo_ciencia)

            }
            2 -> {
                pregunta = preguntasDeHistoria.shuffled()
                binding.background.setImageResource(R.drawable.fondo_historia)

            }
            3 -> {
                pregunta = preguntasDeArte.shuffled()
                binding.background.setImageResource(R.drawable.fondo_arte)

            }
            4 -> {
                pregunta = preguntasDeDeportes.shuffled()
                binding.background.setImageResource(R.drawable.fondo_deporte)

            }
            5 -> {
                pregunta = preguntasDeGeografia.shuffled()
                binding.background.setImageResource(R.drawable.fondo_geografia)

            }
            6 -> {
                pregunta = preguntasDeVideojuegos.shuffled()
                binding.background.setImageResource(R.drawable.fondo_videojuegos)

            }

        }
        actualizarInfo()
        binding.answer1.setOnClickListener(this)
        binding.answer2.setOnClickListener(this)
        binding.answer3.setOnClickListener(this)
        binding.answer4.setOnClickListener(this)

    }

    @SuppressLint("SetTextI18n")
    fun actualizarInfo() {
        binding.timerDown.progress = 1000

        binding.round.text = "${ronda + 1}/5"

        binding.answer1.text = pregunta[ronda][1].split("_")[1]
        binding.answer2.text = pregunta[ronda][2].split("_")[1]
        binding.answer3.text = pregunta[ronda][3].split("_")[1]
        binding.answer4.text = pregunta[ronda][4].split("_")[1]

        binding.answer1.isEnabled = true
        binding.answer2.isEnabled = true
        binding.answer3.isEnabled = true
        binding.answer4.isEnabled = true

        binding.answer1.setBackgroundResource(R.drawable.boton_respuesta)
        binding.answer2.setBackgroundResource(R.drawable.boton_respuesta)
        binding.answer3.setBackgroundResource(R.drawable.boton_respuesta)
        binding.answer4.setBackgroundResource(R.drawable.boton_respuesta)
        binding.question.text = pregunta[ronda][0]
        timer.start()

    }

    override fun onClick(v: View?) {
        val button = v as Button
        val tag = button.tag.toString().toInt()
        if (pregunta[ronda][tag].split("_")[0] == "c" || pregunta[ronda][2] == button.text) {

            binding.answer1.isEnabled = false
            binding.answer2.isEnabled = false
            binding.answer3.isEnabled = false
            binding.answer4.isEnabled = false
            score += binding.timerDown.progress
            button.setBackgroundResource(R.drawable.boton_verde_respuesta)
        } else {
            button.setBackgroundResource(R.drawable.boton_rojo_respuesta)
            respuestaCorrecta()
        }
        timer.cancel()
        delay(1300)
    }

    @SuppressLint("SuspiciousIndentation")
    fun respuestaCorrecta() {

        binding.answer1.isEnabled = false
        binding.answer2.isEnabled = false
        binding.answer3.isEnabled = false
        binding.answer4.isEnabled = false

        if (pregunta[ronda][1].split("_")[0] == "c"){
            binding.answer1.setBackgroundResource(R.drawable.boton_verde_respuesta)
        }
        if (pregunta[ronda][2].split("_")[0] == "c") {
            binding.answer2.setBackgroundResource(R.drawable.boton_verde_respuesta)
        }
        if (pregunta[ronda][3].split("_")[0] == "c"){
            binding.answer3.setBackgroundResource(R.drawable.boton_verde_respuesta)
        }
        if (pregunta[ronda][4].split("_")[0] == "c") {
            binding.answer4.setBackgroundResource(R.drawable.boton_verde_respuesta)
        }
    }

    fun delay(miliSec: Int) {
        timer.cancel()
        ronda++
        Handler(Looper.getMainLooper()).postDelayed({
            if (ronda == 5) {
                changeFragment()
                ronda = 0
            }
            else{
                actualizarInfo()
            }

        }, miliSec.toLong())
    }

    private val timer = object : CountDownTimer(10250, 100) {
        override fun onTick(millisUntilFinished: Long) {
            binding.timerDown.progress -= 1

        }
        override fun onFinish() {
            respuestaCorrecta()
            delay(1300)
        }
    }

    private fun changeFragment() {
        timer.cancel()
        ronda = 0
        findNavController().navigate(R.id.action_playGameTrivialFragment_to_resultTrivialGameFragment)
    }


}