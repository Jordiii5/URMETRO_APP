package com.example.urmetro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.urmetro.databinding.FragmentGaleriaImatgesBinding

class GaleriaImatgesFragment : Fragment() {
    lateinit var binding: FragmentGaleriaImatgesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGaleriaImatgesBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager=GridLayoutManager(requireContext(), 3)

        binding.arrowBack.setOnClickListener {
            findNavController().navigate(R.id.action_galeriaImatgesFragment_to_modulSocialitzacioFragment)
        }
        binding.containerMeves.setOnClickListener {
            findNavController().navigate(R.id.action_galeriaImatgesFragment_to_lesMevesImatgesFragment)
        }
        binding.containerPujar.setOnClickListener {
            findNavController().navigate(R.id.action_galeriaImatgesFragment_to_afegirImatgeFragment)
        }
    }

}