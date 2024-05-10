package com.example.urmetro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.urmetro.databinding.FragmentProgramarRobotBinding

class ProgramarRobotFragment : Fragment() {
    lateinit var binding: FragmentProgramarRobotBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentProgramarRobotBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bloc1= binding.codi1
        val bloc2= binding.codi2
        val bloc3= binding.codi3
        val bloc4= binding.codi4
        val bloc5= binding.codi5
        val bloc6= binding.codi6

        //Fem que els spinners dels blocs de la esquerra no siguin accesibles per a l'usuari
        binding.velocitat.isEnabled = false
        binding.velocitat2.isEnabled = false
        binding.velocitat3.isEnabled = false
        binding.velocitat4.isEnabled = false
        binding.repeticions.isEnabled = false
        binding.repeticions2.isEnabled = false
        binding.repeticions3.isEnabled = false
        binding.repeticions4.isEnabled = false
        binding.segons.isEnabled = false

        binding.moviment.isEnabled=false
        binding.pausa.isEnabled=false
        binding.acabar.isEnabled=false

        //Fem que tots els blocs siguin invisibles
        bloc1.visibility=View.GONE
        bloc1.visibility=View.GONE
        bloc2.visibility=View.GONE
        bloc3.visibility=View.GONE
        bloc4.visibility=View.GONE
        bloc5.visibility=View.GONE
        bloc6.visibility=View.GONE

        binding.arrowBack.setOnClickListener {
            findNavController().navigate(R.id.action_programarRobotFragment_to_rehabilitacioFragment)
        }

        //Al pulsar quest botó mostrem els blocs de moviment y ociltem els de pausa
        binding.moviment.setOnClickListener {
            bloc1.visibility=View.VISIBLE
            bloc2.visibility=View.VISIBLE
            bloc3.visibility=View.VISIBLE
            bloc4.visibility=View.VISIBLE
            bloc5.visibility=View.GONE
            bloc6.visibility=View.GONE
        }
        //Al pulsar quest botó mostrem els blocs de pausa y ociltem els de moviment
        binding.pausa.setOnClickListener {
            bloc1.visibility=View.GONE
            bloc2.visibility=View.GONE
            bloc3.visibility=View.GONE
            bloc4.visibility=View.GONE
            bloc5.visibility=View.VISIBLE
            bloc6.visibility=View.VISIBLE
        }
        //Al pulsar aquest botó fem que la resta de botons siguin accesibles per l'usuari, mostrem el bloc d'inici dins del codi
        binding.inici.setOnClickListener {
            binding.moviment.isEnabled=true
            binding.pausa.isEnabled=true
            binding.acabar.isEnabled=true
            binding.textInici.visibility=View.GONE
            bloc1.visibility=View.VISIBLE
            bloc2.visibility=View.VISIBLE
            bloc3.visibility=View.VISIBLE
            bloc4.visibility=View.VISIBLE
            binding.codiDins1.visibility=View.VISIBLE
        }
        binding.acabar.setOnClickListener {
            binding.codiDins5.visibility=View.VISIBLE
        }
        bloc1.setOnClickListener {
            if(binding.codiDins2.visibility==View.VISIBLE){
                binding.codiDins4.visibility=View.VISIBLE
                binding.flechaHorizontal3.setImageResource(R.drawable.flechas_horizontales)
            }else{
                binding.codiDins2.visibility=View.VISIBLE
                binding.flechaHorizontal2.setImageResource(R.drawable.flechas_horizontales)
            }
        }
        bloc2.setOnClickListener {
            if(binding.codiDins2.visibility==View.VISIBLE){
                binding.codiDins4.visibility=View.VISIBLE
                binding.flechaHorizontal3.setImageResource(R.drawable.flechas_verticales)
            }else{
                binding.codiDins2.visibility=View.VISIBLE
                binding.flechaHorizontal2.setImageResource(R.drawable.flechas_verticales)
            }
        }
        bloc3.setOnClickListener {
            if(binding.codiDins2.visibility==View.VISIBLE){
                binding.codiDins4.visibility=View.VISIBLE
                binding.flechaHorizontal3.setImageResource(R.drawable.flecha_curva)
            }else{
                binding.codiDins2.visibility=View.VISIBLE
                binding.flechaHorizontal2.setImageResource(R.drawable.flecha_curva)
            }
        }
        bloc4.setOnClickListener {
            if(binding.codiDins2.visibility==View.VISIBLE){
                binding.codiDins4.visibility=View.VISIBLE
                binding.flechaHorizontal3.setImageResource(R.drawable.vibracion)
            }else{
                binding.codiDins2.visibility=View.VISIBLE
                binding.flechaHorizontal2.setImageResource(R.drawable.vibracion)
            }

        }
        bloc5.setOnClickListener {
                binding.codiDins3.visibility=View.VISIBLE
        }
        binding.esborrar1.setOnClickListener {
            binding.codiDins2.visibility=View.GONE
        }
        binding.esborrar2.setOnClickListener {
            binding.codiDins3.visibility=View.GONE
        }
        binding.esborrar3.setOnClickListener {
            binding.codiDins4.visibility=View.GONE
        }
    }

}