package com.example.urmetro.viewModel

import android.net.Uri
import android.os.Build
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.urmetro.model.Publicacions
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
    val usuarios: MutableLiveData<List<Usuari>> = MutableLiveData()
    var name = currentUsuari.value?.usuari_nom
    var loginClean = false
    var data = MutableLiveData<List<Usuari>>()
    var dataPub = MutableLiveData<List<Publicacions>>()
    val post= MutableLiveData<Publicacions>()
    val success = MutableLiveData<Boolean>()
    val showToast: MutableLiveData<Boolean> = MutableLiveData()
    var image : Uri? = null
    var fotohecha = true
    var camara = false
    private val _imageUri = MutableLiveData<Uri>()

    val imageUri: LiveData<Uri>
        get() = _imageUri
    fun setImageUri(uri: Uri) {
        _imageUri.value = uri
    }
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

    fun fetchDataPublicacions(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getPost("posts")
            withContext(Dispatchers.Main) {
                if(response.isSuccessful){
                    dataPub.postValue(response.body())
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

    fun updateDades (usuari_nom:String, usuari_telefon: String, usuari_contacte_emergencia: String){
        val usuari_dni = currentUsuari.value?.usuari_dni
        if (usuari_dni != null){
            viewModelScope.launch(Dispatchers.IO){
                try {
                    repository.updateUser(usuari_dni, usuari_nom, usuari_telefon, usuari_contacte_emergencia)
                } catch (e: Exception){
                    Log.d("TRY CATCH FUNCION", "${e.message}")
                }
            }
        }
    }

    suspend fun deleteUser(): Boolean {
        val dniToDelete = currentUsuari.value?.usuari_dni ?: return false

        return withContext(Dispatchers.IO) {
            try {
                val response = repository.deleteUser("/usuaris/$dniToDelete")
                response.isSuccessful
            } catch (e: Exception) {
                Log.e("Error", "Excepción en la corrutina: ${e.message}", e)
                false
            }
        }
    }

    suspend fun deletePublicacio(): Boolean{
        val publicacioToDelete = post.value?.publicacio_id ?: return false
        Log.d("pie de foto", publicacioToDelete.toString())

        return withContext(Dispatchers.IO) {
            try {
                val response = repository.deletePost("/posts/$publicacioToDelete")
                response.isSuccessful
            } catch (e: Exception) {
                Log.e("Error", "Excepción en la corrutina: ${e.message}", e)
                false
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun postPublicacio(post: Publicacions, image: Uri?){
        CoroutineScope(Dispatchers.IO).launch {
            repository.postPublicacio("",post.publicacio_peu_foto,post.publicacio_likes,post.usuari_id,image)
        }
    }


}