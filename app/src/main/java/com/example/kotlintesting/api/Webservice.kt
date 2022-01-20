package com.example.kotlintesting.api

import com.example.kotlintesting.facts.model.FactsModel
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface Webservice {

    @GET("facts.json")
    fun fetchFacts() : Observable<FactsModel>

}