package com.example.urmetro.view

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.urmetro.model.Contacte
import com.example.urmetro.model.Publicacions
import com.example.urmetro.model.Usuari
import com.example.urmetro.viewModel.ApiInterface
import com.google.gson.Gson
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

/**
 * Classe que gestiona les crides a l'API per a les operacions relacionades amb les dades d'usuari i les publicacions.
 * @param dni DNI de l'usuari per a autenticació.
 * @param password Contrasenya de l'usuari per a autenticació.
 */
class ApiRepository(dni: String, password: String) {
    private val apiInterface = ApiInterface.create(dni,password)

    /**
     * Registra un nou usuari.
     * @param usuario Dades de l'usuari a registrar.
     */
    suspend fun register(usuario: Usuari) = apiInterface.register(usuario)

    /**
     * Obté la llista d'usuaris des de la API.
     * @param url URL de la petició.
     */
    suspend fun getUsers(url: String)= apiInterface.getUsers(url)

    /**
     * Inicia sessió per a un usuari existent.
     * @param usuario Dades de l'usuari per a iniciar sessió.
     */
    suspend fun login(usuario: Usuari) = apiInterface.login(usuario)

    /**
     * Actualitza les dades d'un usuari existent.
     * @param usuari_dni DNI de l'usuari.
     * @param usuari_nom Nom de l'usuari.
     * @param usuari_telefon Telèfon de l'usuari.
     * @param usuari_contacte_emergencia Contacte d'emergència de l'usuari.
     */
    suspend fun updateUser(usuari_dni:String, usuari_nom: String, usuari_telefon: String, usuari_contacte_emergencia: String) = apiInterface.updateUsuari(usuari_dni, usuari_nom, usuari_telefon, usuari_contacte_emergencia)

    /**
     * Elimina un usuari existent.
     * @param url URL de la petició.
     */
    suspend fun deleteUser(url: String) = apiInterface.delete(url)

    /**
     * Obté les dades d'un usuari específic.
     * @param url URL de la petició.
     */
    suspend fun getUsuario(url: String) = apiInterface.getUsuari(url)

    /**
     * Obté les dades d'un usuari pel seu identificador.
     * @param url URL de la petició.
     */
    suspend fun getUsuarioId(url: String)= apiInterface.getUsuariId(url)

    /**
     * Obté les publicacions des de la API.
     * @param url URL de la petició.
     */
    suspend fun getPost(url: String) = apiInterface.getPosts(url)

    /**
     * Obté les publicacions pel nom des de la API.
     * @param url URL de la petició.
     */
    suspend fun getPostByName(url: String)= apiInterface.getPostByName(url)

    /**
     * Obté la imatge des de la API.
     * @param url URL de la petició.
     */
    suspend fun getImage(url: String)= apiInterface.getPhoto(url)

    /**
     * Elimina una publicació de la API.
     * @param url URL de la petició.
     */
    suspend fun deletePost(url: String) = apiInterface.delete(url)

    /**
     * Publica una nova publicació a la API.
     * @param postPhoto Foto de la publicació.
     * @param description Descripció de la publicació.
     * @param likes Nombre de likes de la publicació.
     * @param owner Identificador de l'usuari propietari de la publicació.
     * @param file Fitxer de la imatge a pujar.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun postPublicacio(
        postPhoto: String,
        description: String,
        likes: Int,
        owner: Int,
        file: Uri?
    ) {
        val request = Publicacions(0, postPhoto, description,likes,owner)
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
                likes,
                owner
            )
        }
        catch (e: Exception){
            println(e.message)
        }
    }

    /**
     * Obté els contactes des de la API.
     * @param url URL de la petició.
     */
    suspend fun getContactes(url: String) = apiInterface.getContactes(url)

    /**
     * Publica un nou contacte a la API.
     * @param nom Nom del contacte.
     * @param telefon Telèfon del contacte.
     * @param owner Identificador de l'usuari propietari del contacte.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun postContacte(
        nom: String,
        telefon: Int,
        owner: Int
    ) {
        val request = Contacte(0, nom, telefon,owner)
        val gson = Gson()
        return try {
            val response = apiInterface.addContacte(
                nom,
                telefon,
                owner
            )
        }
        catch (e: Exception){
            println(e.message)
        }
    }
}