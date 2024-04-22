package com.example.urmetro

import android.os.Bundle
import android.text.Layout.Directions
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.urmetro.databinding.FragmentMenuBinding
import com.example.urmetro.viewModel.MyViewModel

class MenuFragment : Fragment() {
    lateinit var binding: FragmentMenuBinding
    private val viewModel: MyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.currentUsuari.observe(viewLifecycleOwner) { usuario ->
            usuario?.let {
                binding.nomUsuari.text = "HOLA ${it.usuari_nom?.uppercase()}"
            }
        }

        binding.entreteniment.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_modulEntretenimentFragment)
        }
        binding.sanitat.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_modulSanitatFragment)
        }
        binding.socialitzacio.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_modulSocialitzacioFragment)
        }
        binding.movilitat.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_modulMovilitatFragment)

            binding.perfil.setOnClickListener {
                findNavController().navigate(R.id.action_menuFragment_to_perfilFragment)
            }
        }
        binding.perfil.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_perfilFragment)
        }
    }
}