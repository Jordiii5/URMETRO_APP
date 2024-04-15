package com.example.urmetro.viewModel

import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.urmetro.model.Usuari
import com.example.urmetro.view.ApiRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyViewModel : ViewModel(){
    var users= MutableLiveData<Usuari?>()
    var data= MutableLiveData<List<Usuari>>()
    lateinit var repository: ApiRepository
    val success = MutableLiveData<Boolean>()

    fun getUsers(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getUsers("/usuaris")
            withContext(Dispatchers.Main) {
                if(response.isSuccessful){
                    data.postValue(response.body())
                }
                else{
                    Log.e("Error :", response.message())
                }
            }
        }
    }

    fun getUsuari(dni: String) {
        success.postValue(false)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.getUsuario("/usuaris/$dni")

                if (response.isSuccessful) {
                    if (Looper.myLooper() == Looper.getMainLooper()) {
                        data.value = response.body()
                        success.postValue(true)
                    } else {
                        withContext(Dispatchers.Main) {
                            data.value = response.body()
                            success.postValue(true)
                        }
                    }
                    Log.d("lista", "${users.value}")
                } else {
                    Log.e("Error :", response.message())
                }
            } catch (e: Exception) {
                Log.e("Error", "Excepción en la corrutina: ${e.message}", e)
            }
        }
    }

    fun obtenerNombreUsuario(dni: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.getUsuario("/usuaris/$dni")

                if (response.isSuccessful) {
                    val usuario = response.body()?.firstOrNull() // Suponiendo que obtienes un solo usuario
                    if (usuario != null) {
                        val nombreUsuario = usuario.usuari_nom
                        // Ahora, haz lo que necesites con el nombre de usuario, por ejemplo, actualizar el LiveData
                        users.postValue(usuario)
                    } else {
                        Log.e("Error :", "Usuario no encontrado para el DNI: $dni")
                    }
                } else {
                    Log.e("Error :", response.message())
                }
            } catch (e: Exception) {
                Log.e("Error", "Excepción en la corrutina: ${e.message}", e)
            }
        }
    }
}