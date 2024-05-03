package com.example.urmetro

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.urmetro.databinding.FragmentAfegirImatgeBinding
import com.example.urmetro.model.Publicacions
import com.example.urmetro.viewModel.MyViewModel
import java.io.File

class AfegirImatgeFragment : Fragment() {
    lateinit var binding: FragmentAfegirImatgeBinding
    private val viewModel: MyViewModel by activityViewModels()
    private lateinit var myPreferences: SharedPreferences
    lateinit var uri:Uri

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
                    "",
                    binding.peuImatgeField.text.toString(),
                    0,
                    0
                )
                val archivo = getFileFromUri(requireContext(), uri)

                viewModel.postPublicacio(publicacio, archivo!!)
                findNavController().navigate(R.id.action_afegirImatgeFragment_to_lesMevesImatgesFragment)
            } else {
                Toast.makeText(context, "Error al afegir publicació", Toast.LENGTH_LONG).show()
            }
        }

        binding.publicacioImatge.setOnClickListener {
            openGalleryForImages()
        }
    }

    private fun openGalleryForImages() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        resultLauncher.launch(intent)
    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val image = binding.publicacioImatge

            if(data?.getClipData() != null){
                var count = data.clipData?.itemCount
                for(i in 0..count!! - 1){
                    var imageUri: Uri = data.clipData?.getItemAt(i)!!.uri
                    image.setImageURI(imageUri)
                    uri = imageUri
                }
            }
            else if(data?.getData() != null){
                var imageUri: Uri = data.data!!
                image.setImageURI(imageUri)
                uri = imageUri
            }
        }
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
