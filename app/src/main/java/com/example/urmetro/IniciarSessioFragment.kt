package com.example.urmetro

import android.annotation.SuppressLint
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
import com.example.urmetro.RegistreFragment.HashUtils.hashPassword
import com.example.urmetro.databinding.FragmentIniciarSessioBinding
import com.example.urmetro.model.Usuari
import com.example.urmetro.view.ApiRepository
import com.example.urmetro.viewModel.MyViewModel
import com.example.urmetro.viewModel.PREFS_NAME
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class IniciarSessioFragment : Fragment() {
    lateinit var myPreferences: SharedPreferences
    lateinit var binding: FragmentIniciarSessioBinding
    private val viewModel: MyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIniciarSessioBinding.inflate(layoutInflater)

        // Recuperar datos guardados en SharedPreferences
        val prefs: SharedPreferences =
            requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val savedUsername = prefs.getString("username", "")
        val savedPassword = prefs.getString("password", "")
        val savedHashedPassword = prefs.getString("hashedPassword", "")
        val saveCredentials = prefs.getBoolean("saveCredentials", false)

        if (saveCredentials && savedUsername!!.isNotEmpty() && savedPassword!!.isNotEmpty()) {
            // Si hay credenciales guardadas y válidas, intenta iniciar sesión automáticamente
            attemptLogin(savedUsername, savedPassword)
        }

        binding.dniField.setText(savedUsername)
        binding.contrasenyaField.setText(savedPassword)
        binding.rememberCheckbox.isChecked = saveCredentials

        if (viewModel.loginClean){
            binding.dniField.text?.clear()
            binding.contrasenyaField.text?.clear()
            binding.rememberCheckbox.isChecked = false
            viewModel.loginClean = false
        }
        return binding.root
    }
    @SuppressLint("ResourceType", "CommitPrefEdits")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //myPreferences = requireActivity().getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
        //setupForm()

/*
        if (binding.rememberCheckbox.isChecked){
            findNavController().navigate(R.id.action_iniciarSessioFragment_to_menuFragment)
        }

 */

        binding.login.setOnClickListener {
            binding.carrega.visibility=View.VISIBLE
            val dni = binding.dniField.text.toString().uppercase()
            val contra = binding.contrasenyaField.text.toString()
            val saveCredentials = binding.rememberCheckbox.isChecked
            val hashedPassword = hashPassword(contra)
            if (dni.isNotEmpty() && contra.isNotEmpty()) {
                val prefs: SharedPreferences.Editor = requireActivity().getSharedPreferences(
                    PREFS_NAME, Context.MODE_PRIVATE).edit()

                if (saveCredentials) {
                    prefs.putString("usuari_dni", dni)
                    prefs.putString("usuari_contra", contra)
                }
                else {
                    prefs.remove("usuari_dni")
                    prefs.remove("usuari_contra")
                }
                prefs.putBoolean("saveCredentials", saveCredentials)
                prefs.apply()

                viewModel.currentUsuari.value = Usuari(0,"", dni.uppercase(), 0, 0, hashedPassword)
                viewModel.repository = ApiRepository(dni, hashedPassword)


                CoroutineScope(Dispatchers.IO).launch {
                    val repository = ApiRepository(dni, contra)
                    val response = repository.login(viewModel.currentUsuari.value!!)

                    withContext(Dispatchers.Main) {
                        if (response.isSuccessful) {
                            CoroutineScope(Dispatchers.IO).launch {
                                viewModel.getUsuari(dni)

                                withContext(Dispatchers.Main){
                                    viewModel.success.observe(viewLifecycleOwner) { success ->
                                        if (success == true){
                                            findNavController().navigate(R.id.action_iniciarSessioFragment_to_menuFragment)
                                        }
                                    }
                                }
                            }
                        } else {
                            Toast.makeText(context, "Error con el email o contraseña, inténtalo de nuevo", Toast.LENGTH_SHORT).show()
                            binding.carrega.visibility=View.INVISIBLE
                        }
                    }
                }
            } else {
                Toast.makeText(context, "Has dejado espacios vacíos, rellénalos todos", Toast.LENGTH_SHORT).show()
                binding.carrega.visibility=View.INVISIBLE
            }
        }
        binding.registrat.setOnClickListener {
            findNavController().navigate(R.id.action_iniciarSessioFragment_to_registreFragment)
        }
    }

//    private fun rememberUser(userName: String, pass: String, remember: Boolean) {
//        if(remember){
//            myPreferences.edit {
//                putString("dni", userName)
//                putString("password", pass)
//                putBoolean("remember", remember)
//                putBoolean("active", remember)
//                apply()
//            }
//        }else{
//            myPreferences.edit {
//                putString("dni", "")
//                putString("password", "")
//                putBoolean("remember", remember)
//                putBoolean("active", remember)
//                apply()
//            }
//        }
//
//    }
//    private fun setupForm() {
//        val username = myPreferences.getString("dni", "")
//        val pass = myPreferences.getString("password", "")
//        val remember = myPreferences.getBoolean("remember", false)
//        if (username != null) {
//            if(username.isNotEmpty()){
//                binding.dniField.setText(username)
//                binding.contrasenyaField.setText(pass)
//                binding.rememberCheckbox.isChecked = remember
//            }
//        }
//    }
    @SuppressLint("ResourceType")
    private fun attemptLogin(username: String, password: String) {
        val hashedPassword = hashPassword(password) // Hashear la contraseña ingresada

        // Obtener la contraseña hasheada almacenada en SharedPreferences
        val prefs: SharedPreferences =
            requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val savedHashedPassword = prefs.getString("hashedPassword", hashedPassword)

        if (hashedPassword == savedHashedPassword) {
            // Contraseña válida, proceder con el inicio de sesión
            viewModel.currentUsuari.value = Usuari(0, "", username, 0, 0, password )
            viewModel.repository = ApiRepository(username, hashedPassword)

            CoroutineScope(Dispatchers.IO).launch {
                val repository = ApiRepository(username, hashedPassword)
                val response = repository.login(viewModel.currentUsuari.value!!)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        CoroutineScope(Dispatchers.IO).launch {
                            viewModel.getUsuari(username)
                            withContext(Dispatchers.Main) {
                                viewModel.success.observe(viewLifecycleOwner) { success ->
                                    if (success == true) {
                                        findNavController().navigate(R.id.action_iniciarSessioFragment_to_menuFragment)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}