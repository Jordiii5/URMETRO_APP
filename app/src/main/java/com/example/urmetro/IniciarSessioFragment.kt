package com.example.urmetro

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

/**
 * Fragment per iniciar sessió, permetent als usuaris entrar amb les seves credencials.
 */
class IniciarSessioFragment : Fragment() {
    lateinit var binding: FragmentIniciarSessioBinding
    private val viewModel: MyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIniciarSessioBinding.inflate(layoutInflater)

        // Recuperar les dades guardades a SharedPreferences
        val prefs: SharedPreferences =
            requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val savedUsername = prefs.getString("username", "")
        val savedPassword = prefs.getString("password", "")
        val saveCredentials = prefs.getBoolean("saveCredentials", false)

        if (saveCredentials && savedUsername!!.isNotEmpty() && savedPassword!!.isNotEmpty()) {
            // Si hi ha credencials guardades i vàlides, intenta iniciar sessió automàticament
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

    /**
     * Intenta iniciar sessió automàticament amb les credencials guardades.
     */
    @SuppressLint("ResourceType")
    private fun attemptLogin(username: String, password: String) {
        val hashedPassword = hashPassword(password) // Hashejar la contrasenya introduïda

        // Obtenir la contrasenya hashejada emmagatzemada a SharedPreferences
        val prefs: SharedPreferences =
            requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val savedHashedPassword = prefs.getString("hashedPassword", hashedPassword)

        if (hashedPassword == savedHashedPassword) {
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