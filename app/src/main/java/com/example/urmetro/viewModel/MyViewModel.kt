package com.example.urmetro.viewModel

import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.urmetro.model.Usuari
import com.example.urmetro.view.ApiRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyViewModel : ViewModel(){
    lateinit var repository: ApiRepository
    var currentUsuari= MutableLiveData<Usuari>()
    var name = ""
    var loginClean = false
    val success = MutableLiveData<Boolean>()

    fun getUsuari(dni: String) {
        success.postValue(false)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.getUsuario("/usuaris/$dni")

                if (response.isSuccessful) {
                    if (Looper.myLooper() == Looper.getMainLooper()) {
                        currentUsuari.value = response.body()
                        success.postValue(true)
                    } else {
                        withContext(Dispatchers.Main) {
                            currentUsuari.value = response.body()
                            success.postValue(true)
                        }
                    }
                    Log.d("lista", "${currentUsuari.value}")
                } else {
                    Log.e("Error :", response.message())
                }
            } catch (e: Exception) {
                Log.e("Error", "Excepción en la corrutina: ${e.message}", e)
            }
        }
    }
}