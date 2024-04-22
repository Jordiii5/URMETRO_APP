package com.example.urmetro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.urmetro.databinding.FragmentModulSocialitzacioBinding

class ModulSocialitzacioFragment : Fragment() {
    lateinit var binding: FragmentModulSocialitzacioBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentModulSocialitzacioBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.menu.setOnClickListener {
            findNavController().navigate(R.id.action_modulSocialitzacioFragment_to_menuFragment)
        }
        binding.containerContactes.setOnClickListener {
            findNavController().navigate(R.id.action_modulSocialitzacioFragment_to_llistaContactesFragment)
        }
        binding.containerImatges.setOnClickListener {
            findNavController().navigate(R.id.action_modulSocialitzacioFragment_to_galeriaImatgesFragment)
        }
        binding.help.setOnClickListener {
            findNavController().navigate(R.id.action_modulSocialitzacioFragment_to_ajudaSocialitzacioFragment)
        }
    }

}