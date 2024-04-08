package com.example.urmetro

import android.os.Bundle
import android.text.Layout.Directions
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.urmetro.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.entreteniment.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_modulEntretenimentFragment)
        }
        binding.sanitat.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_modulSanitatFragment)
        }
        binding.socialitzacio.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_modulSocialitzacioFragment)
        }
        binding.movilitat.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_modulMovilitatFragment)
        }
    }

}