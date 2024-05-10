package com.example.urmetro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.urmetro.databinding.FragmentAjudaSocialitzacioBinding


class AjudaSocialitzacioFragment : Fragment() {

    lateinit var binding: FragmentAjudaSocialitzacioBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentAjudaSocialitzacioBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val text=binding.text

        //Llista de textos d'ajuda
        val llistaText= listOf<String>(
        "En el nostre apartat de socialització, et proporcionem connectar-te amb els teus amics i familiars de manera fàcil. A la llista de contactes, trobaràs les persones amb les quals pots parlar. A més, a la galeria de la comunitat, veuràs fotos dels teus contactes. És genial per mantenir-se en contacte! Per començar, simplement toca un nom a la llista de contactes per trucar o enviar un missatge. I si vols veure què estan fent els teus amics, només desplaça't cap a la galeria. Gaudeix connectant amb els teus estimats!",
        "En l'apartat de llista de contactes, trobaràs tots els teus contactes actuals. Pots afegir-ne més a la teva compte si ho desitges. A més, hi ha un botó especial per trucar a un contacte d'emergència, per a aquells moments crítics. També trobaràs un botó dedicat per trucar al 112 en situacions d'emergència extrema. Això et proporciona una manera ràpida i fàcil de contactar amb l'ajuda quan més ho necessitis. Sigues conscient que aquesta funció és per a urgències reals i és important utilitzar-la amb responsabilitat.",
        "En l'apartat de galeria d'imatges, trobaràs totes les imatges que tu i els teus contactes heu compartit. Pots revisar les fotos que has publicat prèviament i també tens l'opció de compartir-ne tantes com vulguis. Aquesta funció et permet mantenir-te al dia amb els moments compartits amb els teus estimats. És fàcil i divertit compartir records i moments especials amb els teus amics i familiars. Simplement selecciona les fotos que vulguis compartir o revisa els records en la galeria. És una manera meravellosa de connectar i reviure moments especials amb els teus estimats."
        )
        var posicio=0
        text.text = llistaText[posicio]
        binding.arrowBack.visibility=View.INVISIBLE

        //Navegació a fragment anterior
        binding.exit.setOnClickListener {
            findNavController().navigate(R.id.action_ajudaSocialitzacioFragment_to_modulSocialitzacioFragment)
        }
        var zoom=true
        binding.arrowBack.setOnClickListener {
            binding.arrowNext.visibility=View.VISIBLE
            binding.arrowNext.isClickable=true

            //Si hi ha una posició enrere la mostrem
            if (posicio>=0){
                posicio--
                text.text = llistaText[posicio]
            }
            //Si no hi ha mes posicions enrere ocultem el botó
            if (posicio<=0){
                binding.arrowBack.visibility=View.INVISIBLE
                binding.arrowBack.isClickable=false
            }

            //Per a cada posició cambiem la marca corresponent
            when(posicio){
                0-> {
                    binding.marca1.setImageResource(R.drawable.baseline_circle_socialitzacio)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca3.setImageResource(R.drawable.baseline_circle_24)
                }
                1-> {
                    binding.marca1.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_socialitzacio)
                    binding.marca3.setImageResource(R.drawable.baseline_circle_24)
                }
                2-> {
                    binding.marca1.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca3.setImageResource(R.drawable.baseline_circle_socialitzacio)
                }

            }
            binding.scroll.scrollTo(0, -10000)
        }
        binding.arrowNext.setOnClickListener {
            binding.arrowBack.visibility=View.VISIBLE
            binding.arrowBack.isClickable=true

            //Si hi ha mes posicions per devant la mostrem
            if (posicio<=llistaText.lastIndex) {
                posicio++
                text.text = llistaText[posicio]
            }
            //Si no hi ha més posicions per devant ocultem el botó
            if (posicio>=llistaText.lastIndex){
                binding.arrowNext.visibility=View.INVISIBLE
                binding.arrowNext.isClickable=false
            }

            //Per a cada posició cambiem la marca corresponent
            when(posicio){
                0-> {
                    binding.marca1.setImageResource(R.drawable.baseline_circle_socialitzacio)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca3.setImageResource(R.drawable.baseline_circle_24)
                }
                1-> {
                    binding.marca1.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_socialitzacio)
                    binding.marca3.setImageResource(R.drawable.baseline_circle_24)
                }
                2-> {
                    binding.marca1.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca3.setImageResource(R.drawable.baseline_circle_socialitzacio)
                }
            }
            binding.scroll.scrollTo(0, -10000)
        }

        //Al pulsar aquest botó cambiem la mida del text de 30 a 40 o al reves
        binding.zoom.setOnClickListener {
            zoom=!zoom
            if (zoom) text.textSize=30F
            else  text.textSize=40F

        }

    }


}