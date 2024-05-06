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

        binding.moviment.setOnClickListener {
            bloc1.visibility=View.VISIBLE
            bloc2.visibility=View.VISIBLE
            bloc3.visibility=View.VISIBLE
            bloc4.visibility=View.VISIBLE
            bloc5.visibility=View.GONE
            bloc6.visibility=View.GONE
        }
        binding.pausa.setOnClickListener {
            bloc1.visibility=View.GONE
            bloc2.visibility=View.GONE
            bloc3.visibility=View.GONE
            bloc4.visibility=View.GONE
            bloc5.visibility=View.VISIBLE
            bloc6.visibility=View.VISIBLE
        }
        binding.inici.setOnClickListener {
            binding.moviment.isEnabled=true
            binding.pausa.isEnabled=true
            bloc1.visibility=View.VISIBLE
            bloc2.visibility=View.VISIBLE
            bloc3.visibility=View.VISIBLE
            bloc4.visibility=View.VISIBLE
            binding.codiDins1.visibility=View.VISIBLE
        }
    }

}