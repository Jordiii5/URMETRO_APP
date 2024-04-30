package com.example.urmetro.viewModel

import com.burgstaller.okhttp.digest.DigestAuthenticator
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.burgstaller.okhttp.digest.Credentials
import com.example.urmetro.model.Usuari
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url
import retrofit2.Response
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiInterface {
    @GET
    suspend fun getUsers(@Url url: String): Response<List<Usuari>>
    @GET
    suspend fun getUsuari(@Url url: String): Response<Usuari>
    @POST("user/login")
    suspend fun login(@Body usuario: Usuari): Response<ResponseBody>
    @POST("user/register")
    suspend fun register(@Body usuario: Usuari): Response<ResponseBody>
    @PUT("usuaris/update/dades/{usuari_dni}/{usuari_nom}/{usuari_telefon}/{usuari_contacte_emergencia}")
    suspend fun updateUsuari(@Path("usuari_dni") usuari_dni: String, @Path("usuari_nom") usuari_nom: String, @Path("usuari_telefon") usuari_telefon: String, @Path("usuari_contacte_emergencia") usuari_contacte_emergencia: String): Response<ResponseBody>
    @DELETE()
    suspend fun deletePost(@Url url: String): Response<Boolean>

    companion object {
        val BASE_URL = "http://172.23.6.131:8080/"

        fun create(dni: String, password: String): ApiInterface {
            val digestAuthenticator = DigestAuthenticator(Credentials(dni, password))

            val client = OkHttpClient.Builder()
                .authenticator(digestAuthenticator)
                .build()

            val gson = GsonBuilder().setLenient().create()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }

}