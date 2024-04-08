package com.example.urmetro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.urmetro.databinding.FragmentModulEntretenimentBinding

class ModulEntretenimentFragment : Fragment() {
    lateinit var binding: FragmentModulEntretenimentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentModulEntretenimentBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.menu.setOnClickListener {
            findNavController().navigate(R.id.action_modulEntretenimentFragment_to_menuFragment)
        }
    }

}