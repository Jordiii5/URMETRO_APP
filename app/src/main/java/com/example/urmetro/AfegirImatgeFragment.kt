package com.example.urmetro

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.urmetro.databinding.FragmentAfegirImatgeBinding
import com.example.urmetro.model.Publicacions
import com.example.urmetro.viewModel.MyViewModel
import java.io.File

/**
 * Fragment per afegir una nova imatge amb peu de foto a la galeria d'imatges.
 */
class AfegirImatgeFragment : Fragment() {
    lateinit var binding: FragmentAfegirImatgeBinding // Binding per accedir als elements de la UI
    private val viewModel: MyViewModel by activityViewModels() // ViewModel per gestionar les dades i l'estat de l'aplicació
    private lateinit var myPreferences: SharedPreferences // Preferències de l'usuari
    var uri = ""  // URI de la imatge seleccionada

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAfegirImatgeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observar els canvis en l'usuari actual i actualitzar la vista amb el nom de l'usuari
        viewModel.currentUsuari.observe(viewLifecycleOwner) { usuario ->
            usuario?.let {
                binding.nomUsuari.text = it.usuari_nom?.uppercase()
            }
        }

        // Inicialitzar les preferències compartides de l'usuari
        myPreferences = requireActivity().getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)

        // Configurar el listener per al botó de retrocés
        binding.arrowBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        // Configurar el listener per al botó d'afegir publicació
        binding.afegirPublicacioButton.setOnClickListener {
            if (binding.peuImatgeField.text.toString().isNotBlank() && viewModel.fotohecha) {
                // Crear una nova publicació amb la informació proporcionada
                val publicacio = Publicacions(
                    0,
                    binding.publicacioImatge.toString(),
                    binding.peuImatgeField.text.toString(),
                    0,
                    viewModel.currentUsuari.value!!.usuari_id
                )
                // Publicar la nova publicació
                viewModel.postPublicacio(publicacio, viewModel.image)
                Log.d("pie de foto", binding.peuImatgeField.text.toString())
                Toast.makeText(context, "Publiació feta correctament", Toast.LENGTH_LONG).show()
                // Navegar a la pantalla de les pròpies imatges
                findNavController().navigate(R.id.action_afegirImatgeFragment_to_lesMevesImatgesFragment)
            } else {
                Toast.makeText(context, "Error al afegir publicació", Toast.LENGTH_LONG).show()
            }
        }

        // Configurar el listener per a la imatge de la publicació, per obrir la càmera
        binding.publicacioImatge.setOnClickListener {
            findNavController().navigate(R.id.action_afegirImatgeFragment_to_camaraFragment)
            viewModel.camara = true
        }
    }

    // Funció per carregar la imatge capturada de la càmera
    private fun loadCapturedImage() {
        if (viewModel.camara) {
            viewModel.camara = false
            uri =
                viewModel.image.toString()
            if (uri.isNotEmpty()) {
                Glide.with(requireContext())
                    .load(uri.toUri())
                    .centerCrop()
                    .transform(RoundedCorners(50))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.publicacioImatge)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Carregar la imatge capturada quan es reanuda el fragment
        loadCapturedImage()
        // Amagar la barra d'acció quan es reanuda el fragment
        val supportActionBar: ActionBar? = (requireActivity() as AppCompatActivity).supportActionBar
        if (supportActionBar != null) supportActionBar.hide()
    }

}
