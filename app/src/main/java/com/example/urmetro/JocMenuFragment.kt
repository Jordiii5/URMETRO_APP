package com.example.urmetro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.urmetro.databinding.FragmentJocMenuBinding


class JocMenuFragment : Fragment() {

    lateinit var binding: FragmentJocMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentJocMenuBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.play.setOnClickListener {
            findNavController().navigate(R.id.action_jocMenuFragment_to_jocSpaceFragment)
        }

        binding.help.setOnClickListener {
            findNavController().navigate(R.id.action_jocMenuFragment_to_helpSpaceJocFragment)
        }
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_jocMenuFragment_to_jocsFragment)
        }
    }


}