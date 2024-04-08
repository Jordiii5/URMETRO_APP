package com.example.urmetro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.urmetro.databinding.FragmentIniciarSessioBinding

class IniciarSessioFragment : Fragment() {
    lateinit var binding: FragmentIniciarSessioBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIniciarSessioBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.login.setOnClickListener {
            findNavController().navigate(R.id.action_iniciarSessioFragment_to_menuFragment)
        }
        binding.registrat.setOnClickListener {
            findNavController().navigate(R.id.action_iniciarSessioFragment_to_registreFragment)
        }
    }

}