package com.example.urmetro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.urmetro.databinding.FragmentAjudaSanitatBinding

class AjudaSanitatFragment : Fragment() {

    lateinit var binding: FragmentAjudaSanitatBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentAjudaSanitatBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val text=binding.text

        val llistaText= listOf<String>(
            "Prova 1",
            "Prova 2",
            "Prova 3",
            "Prova 4")
        var posicio=0
        text.text = llistaText[posicio]
        binding.arrowBack.visibility=View.INVISIBLE

        binding.exit.setOnClickListener {
            findNavController().navigate(R.id.action_ajudaSanitatFragment_to_modulSanitatFragment)
        }
        binding.arrowBack.setOnClickListener {
            binding.arrowNext.visibility=View.VISIBLE
            binding.arrowNext.isClickable=true
            if (posicio>=0){
                posicio--
                text.text = llistaText[posicio]
            }
            if (posicio<=0){
                binding.arrowBack.visibility=View.INVISIBLE
                binding.arrowBack.isClickable=false
            }
            when(posicio){
                0-> {
                    binding.marca1.setImageResource(R.drawable.baseline_circle_sanitat)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca3.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca4.setImageResource(R.drawable.baseline_circle_24)

                }
                1-> {
                    binding.marca1.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_sanitat)
                    binding.marca3.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca4.setImageResource(R.drawable.baseline_circle_24)

                }
                2-> {
                    binding.marca1.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca3.setImageResource(R.drawable.baseline_circle_sanitat)
                    binding.marca4.setImageResource(R.drawable.baseline_circle_24)

                }
                3-> {
                    binding.marca1.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca3.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca4.setImageResource(R.drawable.baseline_circle_sanitat)

                }

            }

        }
        binding.arrowNext.setOnClickListener {
            binding.arrowBack.visibility=View.VISIBLE
            binding.arrowBack.isClickable=true
            if (posicio<=llistaText.lastIndex) {
                posicio++
                text.text = llistaText[posicio]
            }
            if (posicio>=llistaText.lastIndex){
                binding.arrowNext.visibility=View.INVISIBLE
                binding.arrowNext.isClickable=false
            }
            when(posicio){
                0-> {
                    binding.marca1.setImageResource(R.drawable.baseline_circle_sanitat)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca3.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca4.setImageResource(R.drawable.baseline_circle_24)

                }
                1-> {
                    binding.marca1.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_sanitat)
                    binding.marca3.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca4.setImageResource(R.drawable.baseline_circle_24)

                }
                2-> {
                    binding.marca1.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca3.setImageResource(R.drawable.baseline_circle_sanitat)
                    binding.marca4.setImageResource(R.drawable.baseline_circle_24)

                }
                3-> {
                    binding.marca1.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca3.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca4.setImageResource(R.drawable.baseline_circle_sanitat)

                }
                4-> {
                    binding.marca1.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca2.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca3.setImageResource(R.drawable.baseline_circle_24)
                    binding.marca4.setImageResource(R.drawable.baseline_circle_24)

                }
            }
        }
    }


}