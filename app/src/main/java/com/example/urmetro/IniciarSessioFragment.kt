package com.example.urmetro

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.urmetro.databinding.FragmentIniciarSessioBinding
import com.example.urmetro.model.Usuari
import com.example.urmetro.view.ApiRepository
import com.example.urmetro.viewModel.MyViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class IniciarSessioFragment : Fragment() {
    lateinit var myPreferences: SharedPreferences
    lateinit var binding: FragmentIniciarSessioBinding
    private val viewModel: MyViewModel by activityViewModels()
    private lateinit var recordam:Switch

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIniciarSessioBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myPreferences = requireActivity().getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
        setupForm()

        /*
        if (binding.recordam.isChecked){
            findNavController().navigate(R.id.action_iniciarSessioFragment_to_menuFragment)
        }

         */


        binding.login.setOnClickListener {
            val dni = binding.dniField.text.toString().uppercase()
            val contra = binding.contrasenyaField.text.toString()
            recordam=binding.root.findViewById(R.id.login_switch)
            val check = recordam.isChecked


            if (dni.isNotEmpty() && contra.isNotEmpty()) {
                viewModel.users.value = Usuari(0,"", dni, "", 0, 0, "", contra)
                viewModel.repository = ApiRepository(dni, contra)


                CoroutineScope(Dispatchers.IO).launch {
                    val repository = ApiRepository(dni, contra)
                    val response = repository.login(viewModel.users.value!!)
                    try {
                        withContext(Dispatchers.Main) {
                            if (response.isSuccessful) {
                                rememberUser(dni, contra, check)
                                findNavController().navigate(R.id.action_iniciarSessioFragment_to_menuFragment)
                            } else {
                                Toast.makeText(context, "Error con el email o contraseña, inténtalo de nuevo", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(context, "Error en la solicitud, inténtalo de nuevo", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(context, "Has dejado espacios vacíos, rellénalos todos", Toast.LENGTH_SHORT).show()
            }
        }
        binding.registrat.setOnClickListener {
            findNavController().navigate(R.id.action_iniciarSessioFragment_to_registreFragment)
        }
    }

    private fun rememberUser(userName: String, pass: String, remember: Boolean) {
        if(remember){
            myPreferences.edit {
                putString("dni", userName)
                putString("password", pass)
                putBoolean("remember", remember)
                putBoolean("active", remember)
                apply()
            }
        }else{
            myPreferences.edit {
                putString("dni", "")
                putString("password", "")
                putBoolean("remember", remember)
                putBoolean("active", remember)
                apply()
            }
        }

    }
    private fun setupForm() {
        val username = myPreferences.getString("dni", "")
        val pass = myPreferences.getString("password", "")
        val remember = myPreferences.getBoolean("remember", false)
        if (username != null) {
            if(username.isNotEmpty()){
                binding.dniField.setText(username)
                binding.contrasenyaField.setText(pass)
                recordam.isChecked = remember
            }
        }
    }

}