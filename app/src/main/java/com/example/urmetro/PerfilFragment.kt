package com.example.urmetro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.urmetro.databinding.FragmentPerfilBinding
import com.example.urmetro.viewModel.MyViewModel
import kotlinx.coroutines.launch

/**
 * Fragment que mostra el perfil de l'usuari actual i permet realitzar accions com editar perfil,
 * tancar sessió i eliminar compte.
 */
class PerfilFragment : Fragment() {
    lateinit var binding: FragmentPerfilBinding
    private val viewModel: MyViewModel by activityViewModels()

    /**
     * Crea i retorna la vista associada al fragment.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPerfilBinding.inflate(layoutInflater)
        return binding.root
    }

    /**
     * Configura els elements de la vista i estableix els listeners per als botons.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observa els canvis en l'usuari actual i actualitza la vista
        viewModel.currentUsuari.observe(viewLifecycleOwner) { usuario ->
            usuario?.let {
                binding.nomField.text = it.usuari_nom.uppercase()
                binding.dniField.text = it.usuari_dni.uppercase()
                binding.telefonField.text = it.usuari_telefon.toString()
                binding.telefonEmergeniciaField.text = it.usuari_contacte_emergencia.toString()
            }
        }

        // Navegació cap enrere
        binding.arrowBack.setOnClickListener {
            findNavController().navigate(R.id.action_perfilFragment_to_menuFragment)
        }

        // Navegació per editar perfil
        binding.editButton.setOnClickListener {
            findNavController().navigate(R.id.action_perfilFragment_to_editarPerfilFragment)
        }

        // Tancar sessió
        binding.tancarSessi.setOnClickListener {
            Toast.makeText(requireContext(), "Sessió tancada correctament", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_perfilFragment_to_iniciarSessioFragment)
        }

        // Eliminar compte
        binding.bin.setOnClickListener {
            viewModel.viewModelScope.launch {
                viewModel.deleteUser()
                Toast.makeText(requireContext(), "Compta eliminada correctament", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_perfilFragment_to_iniciarSessioFragment)
            }
        }
    }

}