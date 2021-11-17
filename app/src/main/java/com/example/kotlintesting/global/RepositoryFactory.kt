package com.example.kotlintesting.global

import com.example.kotlintesting.api.WebserviceApi
import com.example.kotlintesting.facts.repository.FactsRepository

object RepositoryFactory {

    fun createFactsRepository() : FactsRepository {

        val factsApi = WebserviceUtil.instance.retrofit.create(WebserviceApi::class.java)
        return FactsRepository(factsApi)
    }

}