package com.example.urmetro.view

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.urmetro.model.Publicacions
import com.example.urmetro.model.Usuari
import com.example.urmetro.viewModel.ApiInterface
import com.google.gson.Gson
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class ApiRepository(dni: String, password: String) {
    private val apiInterface = ApiInterface.create(dni,password)
    suspend fun register(usuario: Usuari) = apiInterface.register(usuario)
    suspend fun getUsers(url: String)= apiInterface.getUsers(url)
    suspend fun login(usuario: Usuari) = apiInterface.login(usuario)
    suspend fun updateUser(usuari_dni:String, usuari_nom: String, usuari_telefon: String, usuari_contacte_emergencia: String) = apiInterface.updateUsuari(usuari_dni, usuari_nom, usuari_telefon, usuari_contacte_emergencia)
    suspend fun deleteUser(url: String) = apiInterface.deletePost(url)
    suspend fun getUsuario(url: String) = apiInterface.getUsuari(url)
    suspend fun getPost(url: String) = apiInterface.getPosts(url)
    suspend fun getPostByName(url: String)= apiInterface.getPostByName(url)
    suspend fun getImage(url: String)= apiInterface.getPhoto(url)
    suspend fun deletePost(url: String) = apiInterface.deletePost(url)


    /*
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun postPublicacio(
        postPhoto: String,
        description: String,
        owner: Int,
        file: MultipartBody.Part
    ) {
        val request = Publicacions(0, postPhoto, description,0,owner)
        val file= File(file?.path )
        val gson = Gson()
        return try {
            val response = apiInterface.addPost(
                image = MultipartBody.Part
                    .createFormData(
                        "image",
                        file.name,
                        file.asRequestBody()
                    ),
                description,
                owner
            )
        }
        catch (e: Exception){
            println(e.message)
        }
    }

     */

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun postPublicacio(
        postPhoto: String,
        description: String,
        owner: Int,
        file: Uri?
    ) {
        val request = Publicacions(0, postPhoto, description,0,owner)
        val file= File(file?.path )
        val gson = Gson()
        return try {
            val response = apiInterface.addPost(
                image = MultipartBody.Part
                    .createFormData(
                        "image",
                        file.name,
                        file.asRequestBody()
                    ),
                description,
                owner
            )
        }
        catch (e: Exception){
            println(e.message)
        }
    }
}