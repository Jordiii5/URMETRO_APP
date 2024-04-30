package com.example.urmetro

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.OpenableColumns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
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
    private var currentPhotoPath: String? = null
    var uri = ""
    private val viewModel: MyViewModel by activityViewModels()
    private lateinit var outputDirectory: File
    private lateinit var myPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAfegirImatgeBinding.inflate(layoutInflater)
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



        if (uri != "") {
            Glide.with(requireContext())
                .load(uri.toUri())
                .centerCrop()
                .transform(RoundedCorners(50))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.publicacioImatge)
        }

        binding.arrowBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.afegirPublicacioButton.setOnClickListener{
            if (binding.peuImatgeField.text.toString() != "" && viewModel.fotohecha){
                val publicacio= Publicacions(
                    0,
                    "",
                    binding.peuImatgeField.text.toString(),
                    0,
                    0,
                )
                viewModel.postResena(publicacio, viewModel.image)
                findNavController().navigate(R.id.action_afegirImatgeFragment_to_lesMevesImatgesFragment)
            }
            else{
                Toast.makeText(context, "Error al afegir publicació", Toast.LENGTH_LONG).show()
            }

        }

        binding.publicacioImatge.setOnClickListener {
            openGalleryForImages()
        }
    }

    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data

            data?.data?.let { uri ->
                val file = getFileFromUri(requireContext(), uri)
                if (file != null) {
                    binding.publicacioImatge.setImageURI(uri)
                    currentPhotoPath = file.absolutePath
                }
            }
        }
    }

    private fun openGalleryForImages() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        galleryLauncher.launch(intent)
    }

    private fun getFileFromUri(context: Context, uri: Uri): File? {
        val inputStream = context.contentResolver.openInputStream(uri) ?: return null
        val fileName = uri.lastPathSegment ?: "file"
        val directory = context.getExternalFilesDir(null)
        val file = File(directory, fileName)
        inputStream.use { input ->
            file.outputStream().use { output ->
                input.copyTo(output)
            }
        }
        return if (file.exists()) file else null
    }


}