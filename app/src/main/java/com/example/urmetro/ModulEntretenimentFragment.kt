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
import kotlinx.coroutines.delay


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

        var jocs=binding.jocs
        var cançons=binding.cansons
        var audiollibres=binding.audiollibres
        var diaris=binding.periodics
        var pos=1

        binding.audiollibres.setOnClickListener {
            findNavController().navigate(R.id.action_modulEntretenimentFragment_to_audiollibresFragment)
        }
        binding.periodics.setOnClickListener {
            findNavController().navigate(R.id.action_modulEntretenimentFragment_to_periodicsFragment)
        }
       binding.cansons.setOnClickListener {
            findNavController().navigate(R.id.action_modulEntretenimentFragment_to_canconsFragment)
        }
         binding.jocs.setOnClickListener {
            findNavController().navigate(R.id.action_modulEntretenimentFragment_to_jocsFragment)
        }

        binding.menu.setOnClickListener {
            findNavController().navigate(R.id.action_modulEntretenimentFragment_to_menuFragment)
        }
        binding.help.setOnClickListener {
            findNavController().navigate(R.id.action_modulEntretenimentFragment_to_ajudaEntretenimentFragment)
        }

        binding.arrowNext.setOnClickListener {
            pos++
            when(pos){
                1->{
                    jocs.visibility=View.VISIBLE
                    audiollibres.visibility=View.INVISIBLE
                    cançons.visibility=View.INVISIBLE
                    diaris.visibility=View.INVISIBLE
                }
                2->{
                    jocs.visibility=View.INVISIBLE
                    audiollibres.visibility=View.VISIBLE
                    cançons.visibility=View.INVISIBLE
                    diaris.visibility=View.INVISIBLE
                }
                3->{
                    jocs.visibility=View.INVISIBLE
                    audiollibres.visibility=View.INVISIBLE
                    cançons.visibility=View.VISIBLE
                    diaris.visibility=View.INVISIBLE
                }
                4->{
                    jocs.visibility=View.INVISIBLE
                    audiollibres.visibility=View.INVISIBLE
                    cançons.visibility=View.INVISIBLE
                    diaris.visibility=View.VISIBLE
                }
                5->{
                    pos=0
                }
            }

        }
        binding.arrowBack.setOnClickListener {
            pos--
            when(pos){
                1->{
                    jocs.visibility=View.VISIBLE
                    audiollibres.visibility=View.INVISIBLE
                    cançons.visibility=View.INVISIBLE
                    diaris.visibility=View.INVISIBLE
                }
                2->{
                    jocs.visibility=View.INVISIBLE
                    audiollibres.visibility=View.VISIBLE
                    cançons.visibility=View.INVISIBLE
                    diaris.visibility=View.INVISIBLE
                }
                3->{
                    jocs.visibility=View.INVISIBLE
                    audiollibres.visibility=View.INVISIBLE
                    cançons.visibility=View.VISIBLE
                    diaris.visibility=View.INVISIBLE
                }
                4->{
                    jocs.visibility=View.INVISIBLE
                    audiollibres.visibility=View.INVISIBLE
                    cançons.visibility=View.INVISIBLE
                    diaris.visibility=View.VISIBLE
                }
                0->{
                    pos=4
                }
            }

        }

    }







}
