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

/**
 * Fragment per a l'edició del perfil de l'usuari.
 */
class EditarPerfilFragment : Fragment() {
    lateinit var binding: FragmentEditarPerfilBinding
    private val viewModel: MyViewModel by activityViewModels()

    /**
     * Crea i retorna la vista associada al fragment.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditarPerfilBinding.inflate(layoutInflater)
        return binding.root
    }

    /**
     * Configura els elements de la vista i estableix els listeners per als botons.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Navegació cap enrere
        binding.arrowBack.setOnClickListener {
            findNavController().navigate(R.id.action_editarPerfilFragment_to_perfilFragment)
        }

        // Confirmar les dades editades i guardar-les
        binding.confirmarDadesNoves.setOnClickListener{
            val usuari_nom = binding.nomField.text.toString()
            val usuari_telefon = binding.telefonField.text.toString()
            val usuari_contacte_emergencia = binding.telefonEmergeniciaField.text.toString()

            // Actualitzar les dades del perfil a través del ViewModel
            viewModel.updateDades(usuari_nom, usuari_telefon, usuari_contacte_emergencia)

            // Navegació cap al fragment del perfil
            findNavController().navigate(R.id.action_editarPerfilFragment_to_perfilFragment)
        }
    }
}