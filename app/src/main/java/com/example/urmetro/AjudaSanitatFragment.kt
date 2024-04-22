package com.example.urmetro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.urmetro.databinding.FragmentAjudaSanitatBinding
import kotlin.concurrent.thread

class AjudaSanitatFragment : Fragment() {

    lateinit var binding: FragmentAjudaSanitatBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAjudaSanitatBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val text = binding.text

        val llistaText = listOf<String>(
            "En el nostre apartat de salut, et proporcionem eines per cuidar-te millor. Pots fer exercicis de rehabilitació per mantenir el teu cos fort i àgil. També tenim un calendari on pots organitzar les teves cites mèdiques per no oblidar-ne cap. I el millor de tot, pots portar un registre dels teus medicaments per assegurar-te de prendre'ls en el moment adequat. Tot està dissenyat perquè cuidar la teva salut sigui més fàcil i còmode per a tu.\n",
            "L'apartat d'exercicis de rehabilitació inclou una llista de vídeos amb exercicis específics. Pots reproduir-los i aturar-los per seguir-los tranquil·lament, adaptant-te al teu ritme. Aquesta guia visual millora la comprensió i execució dels exercicis, essencials per a la recuperació després de lesions o procediments mèdics. Les rutines ajuden a millorar la força, flexibilitat i mobilitat, afavorint una rehabilitació còmoda i eficaç, adaptada a les teves necessitats i ritme.",
            "En l'apartat de calendari de cites, trobaràs un calendari que conté totes les teves cites mèdiques. A més, tens l'opció d'afegir noves cites amb el nom, hora i comentari. Aquesta característica t'ajuda a organitzar fàcilment les teves visites mèdiques i recordar-les sense problemes. És una eina útil per gestionar la teva agenda de salut i garantir que no et perdis cap cita important.",
            "Aquest apartat de horari medicació us ajudarà a organitzar i recordar les vostres medicines de manera senzilla. Imagina que és el teu propi calendari personalitzat per tenir cura de la teva salut. A cada hora del dia, podreu afegir el nom del medicament, la data, l'hora i la quantitat que has de prendre. A més, rebràs avisos per recordar quan és el moment de prendre cada medicament."
        )
        var posicio = 0
        text.text = llistaText[posicio]
        binding.arrowBack.visibility = View.INVISIBLE

        binding.exit.setOnClickListener {
            findNavController().navigate(R.id.action_ajudaSanitatFragment_to_modulSanitatFragment)
        }
        var zoom=true
        binding.arrowBack.setOnClickListener {
            binding.arrowNext.visibility = View.VISIBLE
            binding.arrowNext.isClickable = true
            if (posicio >= 0) {
                posicio--
                text.text = llistaText[posicio]
            }
            if (posicio <= 0) {
                binding.arrowBack.visibility = View.INVISIBLE
                binding.arrowBack.isClickable = false
            }


            when (posicio) {
                0 -> {
                    binding.marca1.setImageResource(R.drawable.baseline_circle_sanitat)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca3.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca4.setImageResource(R.drawable.baseline_circle_24)

                }

                1 -> {
                    binding.marca1.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_sanitat)
                    binding.marca3.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca4.setImageResource(R.drawable.baseline_circle_24)

                }

                2 -> {
                    binding.marca1.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca3.setImageResource(R.drawable.baseline_circle_sanitat)
                    binding.marca4.setImageResource(R.drawable.baseline_circle_24)

                }

                3 -> {
                    binding.marca1.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca3.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca4.setImageResource(R.drawable.baseline_circle_sanitat)

                }
            }
            binding.scroll.scrollTo(0, -10000)
        }
        binding.arrowNext.setOnClickListener {
            binding.arrowBack.visibility = View.VISIBLE
            binding.arrowBack.isClickable = true
            if (posicio <= llistaText.lastIndex) {
                posicio++
                text.text = llistaText[posicio]
            }
            if (posicio >= llistaText.lastIndex) {
                binding.arrowNext.visibility = View.INVISIBLE
                binding.arrowNext.isClickable = false
            }
            when (posicio) {
                0 -> {
                    binding.marca1.setImageResource(R.drawable.baseline_circle_sanitat)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca3.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca4.setImageResource(R.drawable.baseline_circle_24)

                }

                1 -> {
                    binding.marca1.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_sanitat)
                    binding.marca3.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca4.setImageResource(R.drawable.baseline_circle_24)

                }

                2 -> {
                    binding.marca1.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca3.setImageResource(R.drawable.baseline_circle_sanitat)
                    binding.marca4.setImageResource(R.drawable.baseline_circle_24)

                }

                3 -> {
                    binding.marca1.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca3.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca4.setImageResource(R.drawable.baseline_circle_sanitat)

                }

                4 -> {
                    binding.marca1.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca3.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca4.setImageResource(R.drawable.baseline_circle_24)

                }
            }
            binding.scroll.scrollTo(0, -10000)
        }

        binding.zoom.setOnClickListener {
            zoom=!zoom
            if (zoom) text.textSize=30F
            else  text.textSize=40F

        }


    }


}