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

val PREFS_NAME = "MyPrefsFile"
class MyViewModel : ViewModel(){
    lateinit var repository: ApiRepository
    var currentUsuari= MutableLiveData<Usuari>()
    var name = ""
    var loginClean = false
    var data = MutableLiveData<List<Usuari>>()
    val success = MutableLiveData<Boolean>()
    val showToast: MutableLiveData<Boolean> = MutableLiveData()

    fun fetchData(){
        success.postValue(false)
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getUsers("/usuaris")
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    data.postValue(response.body())
                } else {
                    Log.e("Error:", response.message())
                }
            }
            success.postValue(true)
        }
    }
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