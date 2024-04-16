package com.example.urmetro

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.helper.widget.Carousel
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.urmetro.databinding.FragmentModulEntretenimentBinding
import com.google.android.material.card.MaterialCardView


class ModulEntretenimentFragment : Fragment() {

    private lateinit var binding: FragmentModulEntretenimentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentModulEntretenimentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.audiollibres.setOnClickListener {
            findNavController().navigate(R.id.action_modulEntretenimentFragment_to_audiollibresFragment)
        }
        binding.cansons.setOnClickListener {
            findNavController().navigate(R.id.action_modulEntretenimentFragment_to_periodicsFragment)
        }
       binding.periodics.setOnClickListener {
            findNavController().navigate(R.id.action_modulEntretenimentFragment_to_canconsFragment)
        }
         binding.jocs.setOnClickListener {
            findNavController().navigate(R.id.action_modulEntretenimentFragment_to_jocsFragment)
        }

        binding.menu.setOnClickListener {
            findNavController().navigate(R.id.action_modulEntretenimentFragment_to_menuFragment)
        }
    }







}
