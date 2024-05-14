package com.example.urmetro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.urmetro.databinding.FragmentGaleriaImatgesBinding
import com.example.urmetro.model.Publicacions
import com.example.urmetro.view.AdapterPublicacio
import com.example.urmetro.viewModel.MyViewModel

/**
 * Fragment que mostra una galeria d'imatges i gestiona les interaccions amb elles.
 */
class GaleriaImatgesFragment : Fragment() {
    lateinit var binding: FragmentGaleriaImatgesBinding // Binding per accedir als elements de la UI
    private val viewModel: MyViewModel by activityViewModels()  // ViewModel per gestionar les dades de les publicacions
    private lateinit var userAdapterPost: AdapterPublicacio // Adaptador per a la llista de publicacions

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGaleriaImatgesBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mostrar indicador de càrrega
        binding.carrega.visibility=View.VISIBLE

        // Obtindre dades de les publicacions
        viewModel.fetchDataPublicacions()

        // Observar canvis en les dades de les publicacions
        viewModel.dataPub.observe(viewLifecycleOwner) { listOfPost ->
            // Configurar el RecyclerView amb les publicacions obtingudes
            setUpRecyclerView(listOfPost as MutableList<Publicacions>)
            // Ocultar indicador de càrrega un cop carregades les dades
            binding.carrega.visibility=View.GONE
        }

        // Observar l'èxit de la càrrega de dades
        viewModel.success.observe(viewLifecycleOwner) { success ->
            // Actualitzar visibilitat dels elements segons l'èxit de la càrrega
            if (success != null) {
                binding.carrega.isVisible = !success
                binding.recyclerView.isVisible = success

            }
        }

        // Configurar el layout manager del RecyclerView
        binding.recyclerView.layoutManager=GridLayoutManager(requireContext(), 3)

        // Configurar listener pel botó de retrocés
        binding.arrowBack.setOnClickListener {
            findNavController().navigate(R.id.action_galeriaImatgesFragment_to_modulSocialitzacioFragment)
        }

        // Configurar listener pel contenidor "Meves Imatges"
        binding.containerMeves.setOnClickListener {
            findNavController().navigate(R.id.action_galeriaImatgesFragment_to_lesMevesImatgesFragment)
        }

        // Configurar listener pel contenidor "Pujar Imatge"
        binding.containerPujar.setOnClickListener {
            findNavController().navigate(R.id.action_galeriaImatgesFragment_to_afegirImatgeFragment)
        }
    }

    // Funció per configurar el RecyclerView amb la llista de publicacions
    fun setUpRecyclerView(listOfPost: MutableList<Publicacions>) {
        userAdapterPost = AdapterPublicacio(listOfPost, viewModel)
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = userAdapterPost
        }
    }
}