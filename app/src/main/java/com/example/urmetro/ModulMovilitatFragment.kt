package com.example.urmetro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.urmetro.databinding.FragmentModulMovilitatBinding

class ModulMovilitatFragment : Fragment() {
    lateinit var binding: FragmentModulMovilitatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentModulMovilitatBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.menu.setOnClickListener {
            findNavController().navigate(R.id.action_modulMovilitatFragment_to_menuFragment)
        }
        binding.help.setOnClickListener {
            findNavController().navigate(R.id.action_modulMovilitatFragment_to_ajudaMobilitatFragment)
        }
    }

}