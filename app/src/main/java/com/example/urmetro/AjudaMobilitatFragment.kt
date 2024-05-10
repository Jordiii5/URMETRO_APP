package com.example.urmetro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.urmetro.databinding.FragmentAjudaMobilitatBinding
import com.example.urmetro.databinding.FragmentAjudaSocialitzacioBinding

class AjudaMobilitatFragment : Fragment() {

    lateinit var binding: FragmentAjudaMobilitatBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentAjudaMobilitatBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val text=binding.text

        //Llista de textos d'ajuda
        val llistaText= listOf<String>(
            "Amb un robot Universal Robots, la seva funcionalitat de mobilitat permet controlar-lo utilitzant les fletxes direccionals per moure'l en la direcció desitjada. Aquest sistema de control simplifica l'operació del robot, permetent als operadors ajustar la seva posició amb precisió i facilitat. Mitjançant aquesta interfície intuïtiva, els usuaris poden navegar el robot cap endavant, cap enrere, cap a la dreta o cap a l'esquerra amb un control directe i immediat. Aquesta capacitat millora la versatilitat del robot en entorns industrials, fent que sigui més accessible per a diverses tasques i aplicacions sense la necessitat de programació complicada.",
            "Aquest apartat també compta amb botons predefinits per a accions específiques, les quals ja hem integrat i produeixen les accions requerides sense que l'usuari hagi de tenir el control complert del robot amb les fletxes indicades a l'altre tutorial. El braç robòtic ofereix assistència física en tasques quotidianes com aixecar-se o agafar objectes, entre altres moltes opcions per facilitar la qualitat de vida quotidiana."
         )
        var posicio=0
        text.text = llistaText[posicio]
        binding.arrowBack.visibility=View.INVISIBLE

        //Navegació a fragment anterior
        binding.exit.setOnClickListener {
            findNavController().navigate(R.id.action_ajudaMobilitatFragment_to_modulMovilitatFragment)
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
                    binding.marca1.setImageResource(R.drawable.baseline_circle_mobilitat)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_24)

                }
                1-> {
                    binding.marca1.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_mobilitat)
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
                    binding.marca1.setImageResource(R.drawable.baseline_circle_mobilitat)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_24)
                }
                1-> {
                    binding.marca1.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_mobilitat)
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