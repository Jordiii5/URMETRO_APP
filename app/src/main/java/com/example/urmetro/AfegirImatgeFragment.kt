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

class AfegirImatgeFragment : Fragment() {
    lateinit var binding: FragmentAfegirImatgeBinding
    private val viewModel: MyViewModel by activityViewModels()
    private lateinit var myPreferences: SharedPreferences
    var uri = ""

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

        viewModel.currentUsuari.observe(viewLifecycleOwner) { usuario ->
            usuario?.let {
                binding.nomUsuari.text = it.usuari_nom?.uppercase()
            }
        }

        myPreferences = requireActivity().getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)

        binding.arrowBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.afegirPublicacioButton.setOnClickListener {
            if (binding.peuImatgeField.text.toString().isNotBlank() && viewModel.fotohecha) {
                val publicacio = Publicacions(
                    0,
                    binding.publicacioImatge.toString(),
                    binding.peuImatgeField.text.toString(),
                    0,
                    viewModel.currentUsuari.value!!.usuari_id
                )

                viewModel.postPublicacio(publicacio, viewModel.image)
                Log.d("pie de foto", binding.peuImatgeField.text.toString())
                Toast.makeText(context, "Publiació feta correctament", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_afegirImatgeFragment_to_lesMevesImatgesFragment)
            } else {
                Toast.makeText(context, "Error al afegir publicació", Toast.LENGTH_LONG).show()
            }
        }

        binding.publicacioImatge.setOnClickListener {
            findNavController().navigate(R.id.action_afegirImatgeFragment_to_camaraFragment)
            viewModel.camara = true
        }
    }

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
        loadCapturedImage()
        val supportActionBar: ActionBar? = (requireActivity() as AppCompatActivity).supportActionBar
        if (supportActionBar != null) supportActionBar.hide()
    }

}
