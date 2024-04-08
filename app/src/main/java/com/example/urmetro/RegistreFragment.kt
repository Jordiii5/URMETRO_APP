package com.example.urmetro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.urmetro.databinding.FragmentRegistreBinding

class RegistreFragment : Fragment() {
    lateinit var binding: FragmentRegistreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistreBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.crearCompte.setOnClickListener {
            findNavController().navigate(R.id.action_registreFragment_to_menuFragment)
        }
        binding.iniciaLaSessi.setOnClickListener {
            findNavController().navigate(R.id.action_registreFragment_to_iniciarSessioFragment)
        }
    }

}