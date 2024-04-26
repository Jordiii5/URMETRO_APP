package com.example.urmetro.audiollibres.utils

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.urmetro.R
import com.example.urmetro.audiollibres.viewmodel.BooksViewModel
import com.example.urmetro.databinding.FragmentAudiollibreDetallBinding

class AudiollibreDetallFragment : Fragment() {

    lateinit var binding: FragmentAudiollibreDetallBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding= FragmentAudiollibreDetallBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Fem una navegació a la llista de tots els llibres al polsar en aquest botó
        binding.arrowBack.setOnClickListener {
            findNavController().navigate(R.id.action_audiollibreDetallFragment_to_audiollibresFragment)
        }

        val viewModel = ViewModelProvider(requireActivity())[BooksViewModel::class.java]
        val id = arguments?.getString("id")//Rebem un valor per aquesta key des de la navegació y s'el donem a una variable
        val book = viewModel.getBooks(id!!)//Truquem a la funció del Viewmodel que ens retorna els llibres y filtrem per la id, aixi ens retornará unicament el llibre que necesitem

        //li donem el valor de la url concreta d'aquest llibre a una variable y troquem a la funció pper obrir el navegador
        binding.obrir.setOnClickListener {
            val url= book!!.url_librivox
            openLink(url)
        }


        binding.titol.text = book!!.title//Posem el titol del llibre en la capçalera del fragment
        binding.desc.text = book.description//Posem la descripció concreta d'aquest llibre en el seu camp corresponent

        //Posem les dades del detall de l'autor d'aquest llibre en els seus camps corresponents
        binding.autorNom.text = "Nom: ${book.authors[0].first_name} ${book.authors[0].last_name}"
        binding.autorDob.text = "Any de neixement: ${book.authors[0].dob}"
        binding.autorDod.text = "Any de mort: ${book.authors[0].dod}"

        //Posem les dades del detall d'aquest llibre en els seus camps corresponents
        binding.llibreCopy.text = "Any de publicació: ${book.copyright_year}"
        binding.llibreCapitols.text = "Nombre de capìtols: ${book.num_sections}"
        binding.llibreIdioma.text = "Idioma: ${book.language}"
        binding.llibreTemps.text = "Duració total: ${book.totaltime}"

    }

    /**
     * @author Joel Garcia
     *
     * @param url= url de la pàgina web
     *
     * Aquesta funció agafa la url que rep y obre en navegador amb aquesta url directament
     */
    private fun openLink(url: String) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}