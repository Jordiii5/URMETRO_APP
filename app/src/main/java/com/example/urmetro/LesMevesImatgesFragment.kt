package com.example.urmetro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.urmetro.databinding.FragmentLesMevesImatgesBinding
import com.example.urmetro.databinding.ItemPublicaciosMevesBinding
import com.example.urmetro.model.Publicacions
import com.example.urmetro.view.AdapterMevesPublicacions
import com.example.urmetro.view.AdapterPublicacio
import com.example.urmetro.viewModel.MyViewModel
import kotlinx.coroutines.launch

/**
 * Fragment que mostra les pròpies imatges i gestiona les interaccions amb elles.
 */
class LesMevesImatgesFragment : Fragment() {
    lateinit var binding: FragmentLesMevesImatgesBinding // Binding per accedir als elements de la UI
    private val viewModel: MyViewModel by activityViewModels() // ViewModel per gestionar les dades de les publicacions
    private lateinit var userAdapterPost: AdapterMevesPublicacions // Adaptador per a la llista de les pròpies publicacions
    private lateinit var bindingMevesImatges: ItemPublicaciosMevesBinding // Binding per a elements de la vista de les pròpies publicacions

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLesMevesImatgesBinding.inflate(layoutInflater)
        bindingMevesImatges = ItemPublicaciosMevesBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtenir les dades de les publicacions
        viewModel.fetchDataPublicacions()

        // Observar els canvis en les dades de les publicacions
        viewModel.dataPub.observe(viewLifecycleOwner) { listOfPost ->
            // Filtrar les pròpies publicacions
            val userPosts = listOfPost.filter { it.usuari_id == viewModel.currentUsuari.value?.usuari_id }
            // Configurar el RecyclerView amb les pròpies publicacions
            setUpRecyclerView(userPosts.toMutableList())
        }

        // Configurar el listener per al botó d'esborrar una publicació
        bindingMevesImatges.esborrar.setOnClickListener {
            viewModel.viewModelScope.launch {
                // Mostrar un missatge informatiu d'eliminació
                Toast.makeText(requireContext(), "Publicació eliminada correctament", Toast.LENGTH_SHORT).show()
                // Eliminar la publicació
                viewModel.deletePublicacio()
            }
        }

        // Configurar el listener per al botó de retrocés
        binding.arrowBack.setOnClickListener {
            findNavController().navigate(R.id.action_lesMevesImatgesFragment_to_galeriaImatgesFragment)
        }

    }

    // Funció per configurar el RecyclerView amb la llista de les pròpies publicacions
    fun setUpRecyclerView(listOfPost: MutableList<Publicacions>) {
        userAdapterPost = AdapterMevesPublicacions(listOfPost, viewModel)
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = userAdapterPost
        }
    }


}