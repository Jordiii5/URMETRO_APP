package com.example.urmetro

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.urmetro.databinding.FragmentLlistaContactesBinding

class LlistaContactesFragment : Fragment() {
    lateinit var binding: FragmentLlistaContactesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLlistaContactesBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.arrowBack.setOnClickListener {
            findNavController().navigate(R.id.action_llistaContactesFragment_to_modulSocialitzacioFragment)
        }
        binding.afegirContacteButton.setOnClickListener{
            val builder = AlertDialog.Builder( this.context)
            val view = layoutInflater.inflate(R.layout.dialog_contactes,null)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
        }
    }

}