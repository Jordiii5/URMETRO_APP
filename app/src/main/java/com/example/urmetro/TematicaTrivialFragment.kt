package com.example.urmetro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.urmetro.databinding.FragmentTematicaTrivialBinding

class TematicaTrivialFragment : Fragment() {

    companion object{
        var tematicaPartida = 0
    }
    lateinit var binding: FragmentTematicaTrivialBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTematicaTrivialBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tematica1.setOnClickListener{
            tematicaPartida = 0
            findNavController().navigate(R.id.action_tematicaTrivialFragment_to_playGameTrivialFragment)

        }

        binding.tematica2.setOnClickListener{
            tematicaPartida = 1
            findNavController().navigate(R.id.action_tematicaTrivialFragment_to_playGameTrivialFragment)

        }

        binding.tematica3.setOnClickListener{
            tematicaPartida = 2
            findNavController().navigate(R.id.action_tematicaTrivialFragment_to_playGameTrivialFragment)

        }

        binding.tematica4.setOnClickListener{
            tematicaPartida = 3
            findNavController().navigate(R.id.action_tematicaTrivialFragment_to_playGameTrivialFragment)

        }
        binding.tematica5.setOnClickListener{
            tematicaPartida = 4
            findNavController().navigate(R.id.action_tematicaTrivialFragment_to_playGameTrivialFragment)

        }

        binding.tematica6.setOnClickListener{
            tematicaPartida = 5
            findNavController().navigate(R.id.action_tematicaTrivialFragment_to_playGameTrivialFragment)

        }
        binding.volverMenuTrivial.setOnClickListener{
            findNavController().navigate(R.id.action_tematicaTrivialFragment_to_jocMenuTrivialFragment)

        }


    }


}