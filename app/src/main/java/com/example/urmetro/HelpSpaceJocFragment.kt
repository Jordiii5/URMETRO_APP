package com.example.urmetro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.urmetro.databinding.FragmentHelpSpaceJocBinding


class HelpSpaceJocFragment : Fragment() {


    lateinit var binding: FragmentHelpSpaceJocBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHelpSpaceJocBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.volveralmenu.setOnClickListener {
            findNavController().navigate(R.id.action_helpSpaceJocFragment_to_jocMenuFragment)
        }
    }
}