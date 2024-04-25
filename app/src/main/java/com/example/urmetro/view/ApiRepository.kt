package com.example.urmetro.view

import com.example.urmetro.model.Usuari
import com.example.urmetro.viewModel.ApiInterface

class ApiRepository(dni: String, password: String) {
    private val apiInterface = ApiInterface.create(dni,password)
    suspend fun register(usuario: Usuari) = apiInterface.register(usuario)
    suspend fun getUsers(url: String)= apiInterface.getUsers(url)
    suspend fun login(usuario: Usuari) = apiInterface.login(usuario)
    suspend fun updateUser(usuari_dni:String, usuari_nom: String, usuari_adreça:String, usuari_telefon: String, usuari_contacte_emergencia: String) = apiInterface.updateUsuari(usuari_dni, usuari_nom, usuari_adreça, usuari_telefon, usuari_contacte_emergencia)
    suspend fun deleteUser(url: String) = apiInterface.deletePost(url)
    suspend fun getUsuario(url: String) = apiInterface.getUsuari(url)

}