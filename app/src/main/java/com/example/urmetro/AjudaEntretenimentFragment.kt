package com.example.urmetro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.urmetro.databinding.FragmentAjudaEntretenimentBinding

class AjudaEntretenimentFragment : Fragment() {

    lateinit var binding: FragmentAjudaEntretenimentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentAjudaEntretenimentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val text=binding.text

        //Llista de textos d'ajuda
        val llistaText= listOf<String>(
            "En el nostre apartat d'entreteniment, et donem opcions perquè gaudeixis al màxim. Pots jugar a jocs divertits, escoltar emocionants audiollibres i trobar llistes de música per a qualsevol moment. També pots estar informat amb enllaços a notícies en línia. Volem que t'ho passis bé i trobis el que més t'agrada fer a la nostra app. Així que si busques passar un bon moment, estàs al lloc adequat!",
            "A l'apartat de jocs, trobaràs una varietat d'opcions per a divertir-te. Gaudeix de jocs senzills i addictius dissenyats per entretenir-te en qualsevol moment. Explora la nostra selecció i troba el joc perfecte per a tu!",
            "A l'apartat de diaris, trobaràs quatre diaris reconeguts nacionalment. Cada botó et portarà directament a la pàgina web del diari corresponent, on podràs accedir a les notícies més actualitzades. Mantingues-te al dia amb només un clic!",
            "En l'apartat d'audiollibres, trobaràs una llista de llibres disponibles. Quan seleccionis un llibre, seràs redirigit a la pàgina web on es troba l'audiollibre corresponent. Allà podràs escoltar-lo i gaudir de la història sense haver de llegir. És una manera perfecta de descobrir noves aventures i entretenir-te en qualsevol moment.")
        var posicio=0
        text.text = llistaText[posicio]
        binding.arrowBack.visibility=View.INVISIBLE

        //Navegació a fragment anterior
        binding.exit.setOnClickListener {
            findNavController().navigate(R.id.action_ajudaEntretenimentFragment_to_modulEntretenimentFragment)
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
                    binding.marca1.setImageResource(R.drawable.baseline_circle_entreteniment)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca3.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca4.setImageResource(R.drawable.baseline_circle_24)
                }
                1-> {
                    binding.marca1.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_entreteniment)
                    binding.marca3.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca4.setImageResource(R.drawable.baseline_circle_24)
                }
                2-> {
                    binding.marca1.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca3.setImageResource(R.drawable.baseline_circle_entreteniment)
                    binding.marca4.setImageResource(R.drawable.baseline_circle_24)
                }
                3-> {
                    binding.marca1.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca3.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca4.setImageResource(R.drawable.baseline_circle_entreteniment)
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
                    binding.marca1.setImageResource(R.drawable.baseline_circle_entreteniment)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca3.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca4.setImageResource(R.drawable.baseline_circle_24)
                }
                1-> {
                    binding.marca1.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_entreteniment)
                    binding.marca3.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca4.setImageResource(R.drawable.baseline_circle_24)
                }
                2-> {
                    binding.marca1.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca3.setImageResource(R.drawable.baseline_circle_entreteniment)
                    binding.marca4.setImageResource(R.drawable.baseline_circle_24)
                }
                3-> {
                    binding.marca1.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca3.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca4.setImageResource(R.drawable.baseline_circle_entreteniment)
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