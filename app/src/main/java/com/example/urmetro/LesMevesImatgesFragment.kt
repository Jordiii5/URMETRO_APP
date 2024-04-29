package com.example.urmetro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.urmetro.databinding.FragmentLesMevesImatgesBinding

class LesMevesImatgesFragment : Fragment() {
    lateinit var binding: FragmentLesMevesImatgesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLesMevesImatgesBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.arrowBack.setOnClickListener {
            findNavController().navigate(R.id.action_lesMevesImatgesFragment_to_galeriaImatgesFragment)
        }
    }

}