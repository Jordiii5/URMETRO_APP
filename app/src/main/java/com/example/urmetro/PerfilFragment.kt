package com.example.urmetro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.urmetro.databinding.FragmentPerfilBinding
import com.example.urmetro.viewModel.MyViewModel

class PerfilFragment : Fragment() {
    lateinit var binding: FragmentPerfilBinding
    private val viewModel: MyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPerfilBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.currentUsuari.observe(viewLifecycleOwner) { usuario ->
            usuario?.let {
                binding.nomField.text = it.usuari_nom.uppercase()
                binding.dniField.text = it.usuari_dni.uppercase()
                binding.adressField.text = it.usuari_adreça
                binding.telefonField.text = it.usuari_telefon.toString()
                binding.telefonEmergeniciaField.text = it.usuari_contacte_emergencia.toString()
            }
        }

        binding.arrowBack.setOnClickListener {
            findNavController().navigate(R.id.action_perfilFragment_to_menuFragment)
        }
        binding.editButton.setOnClickListener {
            findNavController().navigate(R.id.action_perfilFragment_to_editarPerfilFragment)
        }
        binding.tancarSessi.setOnClickListener {
            findNavController().navigate(R.id.action_perfilFragment_to_iniciarSessioFragment)
        }
    }

}