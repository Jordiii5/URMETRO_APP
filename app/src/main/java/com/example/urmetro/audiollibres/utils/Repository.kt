package com.example.urmetro.audiollibres.utils

class Repository {
    private val apiInterface = ApiInterface.create()

    suspend fun getTags() = apiInterface.getTags()
}