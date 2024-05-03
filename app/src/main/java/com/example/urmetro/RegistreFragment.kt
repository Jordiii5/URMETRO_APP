package com.example.urmetro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.urmetro.databinding.FragmentRegistreBinding
import com.example.urmetro.model.Usuari
import com.example.urmetro.view.ApiRepository
import com.example.urmetro.viewModel.MyViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.security.MessageDigest

class RegistreFragment : Fragment() {
    lateinit var binding: FragmentRegistreBinding
    private val viewModel: MyViewModel by activityViewModels()

    object HashUtils{
        fun hashPassword(contra: String): String {
            val bytes = contra.toByteArray()
            val md = MessageDigest.getInstance("SHA-256")
            val digest = md.digest(bytes)
            return digest.fold("") { str, it -> str + "%02x".format(it) }
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRegistreBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.crearCompte.setOnClickListener {
            val dni = binding.dniField.text.toString().uppercase()
            val contra = binding.contrasenyaField.text.toString()
            val nom = binding.nameField.text.toString()
            val repetircontra = binding.confirmarContrasenyaField.text.toString()


            if (nom == "" || dni == "" || contra == "" || repetircontra == ""){
                //faltara crear un toast para poder avisar que se ha dejado espacios vacios para registrar un usuario
                Toast.makeText(context, "Has dejado espacios vacíos, rellénalos todos", Toast.LENGTH_SHORT).show()
            }else if (contra != repetircontra){
                //faltara crear un toast para poder avisar que las contras no son iguales
                Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
            else if (dni.length < 9 || dni.length > 9){
                //faltara crear un toast para poder avisar que el dni no puede ser diferente a 9 caracteres
                Toast.makeText(context, "El DNI tiene que contener 9 caracteres.", Toast.LENGTH_SHORT).show()
            }else{
                val hashedPassword = HashUtils.hashPassword(binding.contrasenyaField.text.toString())
                viewModel.currentUsuari.value = Usuari(0,binding.nameField.text.toString(), binding.dniField.text.toString().uppercase(), 0, 0, hashedPassword)
                viewModel.repository = ApiRepository(binding.dniField.text.toString(), hashedPassword)
                CoroutineScope(Dispatchers.IO).launch {
                    val repository = ApiRepository(binding.dniField.text.toString().uppercase(), hashedPassword)
                    val response = repository.register(viewModel.currentUsuari.value!!)
                    withContext(Dispatchers.Main){
                        if (response.isSuccessful){
                            Toast.makeText(context, "Registre correctament", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_registreFragment_to_menuFragment)
                        }else{
                            //faltara crear un toast para poder avisar que el usuario no se ha podido registrar
                            Toast.makeText(context, "Error al registrarse", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
        binding.iniciaLaSessi.setOnClickListener {
            findNavController().navigate(R.id.action_registreFragment_to_iniciarSessioFragment)
        }
    }

}