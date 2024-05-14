package com.example.urmetro.viewModel

import com.burgstaller.okhttp.digest.DigestAuthenticator
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.burgstaller.okhttp.digest.Credentials
import com.example.urmetro.model.Contacte
import com.example.urmetro.model.Publicacions
import com.example.urmetro.model.Usuari
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

/**
 * Interfície que defineix els mètodes per interactuar amb l'API del servidor.
 */
interface ApiInterface {

    /**
     * Obté tots els usuaris.
     *
     * @param url La URL de la petició.
     * @return Una resposta amb una llista d'usuaris si és satisfactòria.
     */
    @GET
    suspend fun getUsers(@Url url: String): Response<List<Usuari>>

    /**
     * Obté un usuari per la URL especificada.
     *
     * @param url La URL de la petició.
     * @return Una resposta amb l'usuari si és satisfactòria.
     */
    @GET
    suspend fun getUsuari(@Url url: String): Response<Usuari>

    /**
     * Obté un usuari per ID.
     *
     * @param url La URL de la petició.
     * @return Una resposta amb l'usuari si és satisfactòria.
     */
    @GET
    suspend fun getUsuariId(@Url url: String): Response<Usuari>

    /**
     * Realitza una autenticació de l'usuari.
     *
     * @param usuario Les dades de l'usuari per a l'autenticació.
     * @return Una resposta amb el resultat de la autenticació.
     */
    @POST("user/login")
    suspend fun login(@Body usuario: Usuari): Response<ResponseBody>

    /**
     * Registra un nou usuari al sistema.
     *
     * @param usuario Les dades del nou usuari.
     * @return Una resposta amb el resultat del registre.
     */
    @POST("user/register")
    suspend fun register(@Body usuario: Usuari): Response<ResponseBody>

    /**
     * Actualitza les dades d'un usuari.
     *
     * @param usuari_dni El DNI de l'usuari.
     * @param usuari_nom El nom de l'usuari.
     * @param usuari_telefon El telèfon de l'usuari.
     * @param usuari_contacte_emergencia El contacte d'emergència de l'usuari.
     * @return Una resposta amb el resultat de l'actualització.
     */
    @PUT("usuaris/update/dades/{usuari_dni}/{usuari_nom}/{usuari_telefon}/{usuari_contacte_emergencia}")
    suspend fun updateUsuari(@Path("usuari_dni") usuari_dni: String, @Path("usuari_nom") usuari_nom: String, @Path("usuari_telefon") usuari_telefon: String, @Path("usuari_contacte_emergencia") usuari_contacte_emergencia: String): Response<ResponseBody>

    /**
     * Obté totes les publicacions.
     *
     * @param url La URL de la petició.
     * @return Una resposta amb una llista de publicacions si és satisfactòria.
     */
    @GET()
    suspend fun getPosts(@Url url: String): Response<List<Publicacions>>

    /**
     * Obté una publicació per nom.
     *
     * @param url La URL de la petició.
     * @return Una resposta amb una llista de publicacions si és satisfactòria.
     */
    @GET()
    suspend fun getPostByName(@Url url: String): Response<List<Publicacions>>

    /**
     * Obté una foto per la URL especificada.
     *
     * @param url La URL de la petició.
     * @return Una resposta amb la foto si és satisfactòria.
     */
    @GET
    suspend fun getPhoto(@Url url: String): Response<ResponseBody>

    /**
     * Afegeix una nova publicació al servidor.
     *
     * @param image La imatge de la publicació.
     * @param description El peu de foto de la publicació.
     * @param likes El nombre de "m'agrada" de la publicació.
     * @param owner L'ID de l'usuari que publica la publicació.
     * @return Una resposta amb la publicació afegida si és satisfactòria.
     */
    @Multipart
    @POST("posts")
    suspend fun addPost(
        @Part image: MultipartBody.Part,
        @Part ("publicacio_peu_foto") description: String,
        @Part ("publicacio_likes") likes: Int,
        @Part ("usuari_id") owner: Int
    ): Response<Publicacions>

    /**
     * Obté tots els contactes disponibles.
     *
     * @param url La URL de la petició.
     * @return Una resposta amb una llista de contactes si és satisfactòria.
     */
    @GET()
    suspend fun getContactes(@Url url: String): Response<List<Contacte>>

    /**
     * Afegeix un nou contacte al servidor.
     *
     * @param nom El nom del contacte.
     * @param telefon El número de telèfon del contacte.
     * @param owner L'ID de l'usuari que afegeix el contacte.
     * @return Una resposta amb el contacte afegit si és satisfactòria.
     */
    @Multipart
    @POST("contactes")
    suspend fun addContacte(
        @Part ("contacte_nom") nom: String,
        @Part ("contacte_telefon") telefon: Int,
        @Part ("usuari_id") owner: Int
    ): Response<Contacte>

    /**
     * Elimina un recurs del servidor.
     *
     * @param url La URL del recurs a eliminar.
     * @return Una resposta amb el resultat de l'eliminació (true si és exitosa, false altrament).
     */
    @DELETE()
    suspend fun delete(@Url url: String): Response<Boolean>

    companion object {
        val BASE_URL = "http://172.23.6.130:8080/"

        /**
         * Crea una nova instància de l'API interfície amb autenticació digest.
         *
         * @param dni El DNI de l'usuari.
         * @param password La contrasenya de l'usuari.
         * @return Una nova instància de l'API interfície amb autenticació configurada.
         */
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