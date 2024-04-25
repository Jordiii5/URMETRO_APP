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
        binding.arrowBack.setOnClickListener {
            findNavController().navigate(R.id.action_audiollibreDetallFragment_to_audiollibresFragment)
        }

        val viewModel = ViewModelProvider(requireActivity())[BooksViewModel::class.java]
        val id = arguments?.getString("id")
        val book = viewModel.getBooks(id!!)
        binding.obrir.setOnClickListener {
            val url= book!!.url_librivox
            openLink(url)
        }


        binding.titol.text = book!!.title
        binding.desc.text = book.description
        binding.autorNom.text = "Nom: ${book.authors[0].first_name} ${book.authors[0].last_name}"
        binding.autorDob.text = "Any de neixement: ${book.authors[0].dob}"
        binding.autorDod.text = "Any de mort: ${book.authors[0].dod}"

        binding.llibreCopy.text = "Any de publicació: ${book.copyright_year}"
        binding.llibreCapitols.text = "Nombre de capìtols: ${book.num_sections}"
        binding.llibreIdioma.text = "Idioma: ${book.language}"
        binding.llibreTemps.text = "duració total: ${book.totaltime}"

    }
    private fun openLink(url: String) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}