package com.example.urmetro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class RegistreFragment : Fragment() {
    lateinit var binding: FragmentRegistreBinding
    private val viewModel: MyViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRegistreBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.crearCompte.setOnClickListener {
            val dni = binding.dniField.text.toString()
            val contra = binding.contrasenyaField.text.toString()
            val nom = binding.nameField.text.toString()
            val repetircontra = binding.confirmarContrasenyaField.text.toString()

            if (dni.length < 9 || dni.length > 9){
                //faltara crear un toast para poder avisar que el dni no puede ser diferente a 9 caracteres
            }else if (contra != repetircontra){
                //faltara crear un toast para poder avisar que las contras no son iguales
            }else{
                viewModel.users.value = listOf(Usuari(0,nom, dni, "", 0, 0, "", contra))
                viewModel.repository = ApiRepository(dni, contra)
                CoroutineScope(Dispatchers.IO).launch {
                    val repository = ApiRepository(dni, contra)
                    val response = repository.register(viewModel.users.value)
                    withContext(Dispatchers.Main){
                        if (response.isSuccessful){
                            findNavController().navigate(R.id.action_registreFragment_to_menuFragment)
                        }else{
                            //faltara crear un toast para poder avisar que el usuario no se ha podido registrar
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