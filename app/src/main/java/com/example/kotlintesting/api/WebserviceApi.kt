package com.example.kotlintesting.api

import com.example.kotlintesting.facts.model.FactsModel
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface WebserviceApi {

    @GET("facts.json")
    fun getFacts() : Single<Response<FactsModel>>

}