package com.example.urmetro

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.urmetro.PlayGameTrivialFragment.Companion.score
import com.example.urmetro.databinding.FragmentResultTrivialGameBinding


class ResultTrivialGameFragment : Fragment() {

    lateinit var binding: FragmentResultTrivialGameBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentResultTrivialGameBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.scorePoints.text = "${score}pts"

        binding.returnMenuButton.setOnClickListener {
            findNavController().navigate(R.id.action_resultTrivialGameFragment_to_jocMenuTrivialFragment)
        }

        binding.returnTematicaButton.setOnClickListener {
            findNavController().navigate(R.id.action_resultTrivialGameFragment_to_tematicaTrivialFragment)
        }




    }
}