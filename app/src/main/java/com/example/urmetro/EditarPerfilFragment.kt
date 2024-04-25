package com.example.urmetro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.urmetro.databinding.FragmentEditarPerfilBinding
import com.example.urmetro.viewModel.MyViewModel

class EditarPerfilFragment : Fragment() {
    lateinit var binding: FragmentEditarPerfilBinding
    private val viewModel: MyViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditarPerfilBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.arrowBack.setOnClickListener {
            findNavController().navigate(R.id.action_editarPerfilFragment_to_perfilFragment)
        }

        binding.confirmarDadesNoves.setOnClickListener{
            val usuari_nom = binding.nomField.text.toString()
            val usuari_adreça = binding.adressField.text.toString()
            val usuari_telefon = binding.telefonField.text.toString()
            val usuari_contacte_emergencia = binding.telefonEmergeniciaField.text.toString()
            viewModel.updateDades(usuari_nom, usuari_adreça, usuari_telefon, usuari_contacte_emergencia)
            findNavController().navigate(R.id.action_editarPerfilFragment_to_perfilFragment)
        }
    }

}