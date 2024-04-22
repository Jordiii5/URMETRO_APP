package com.example.urmetro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.urmetro.databinding.FragmentJocsBinding

class JocsFragment : Fragment() {
    lateinit var binding: FragmentJocsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJocsBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.arrowBack.setOnClickListener {
            findNavController().navigate(R.id.action_jocsFragment_to_modulEntretenimentFragment)
        }
        binding.containerJoc1.setOnClickListener {
            findNavController().navigate(R.id.action_jocsFragment_to_jocMenuFragment)
        }
        binding.containerJoc2.setOnClickListener {
            findNavController().navigate(R.id.action_jocsFragment_to_jocMenuTrivialFragment)
        }
    }

}