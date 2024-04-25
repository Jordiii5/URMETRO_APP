package com.example.urmetro.audiollibres.utils

import com.example.urmetro.audiollibres.model.Data
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    //Aquí posem les operacions GET,POST, PUT i DELETE vistes abans
@GET("audiobooks/?&format=json")
suspend fun getTags(): Response<Data>
    companion object {
        const val BASE_URL = "https://librivox.org/api/feed/"
        fun create(): ApiInterface {
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}