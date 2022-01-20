package com.example.kotlintesting.global

import android.content.Context
import com.example.kotlintesting.api.Webservice
import com.example.kotlintesting.facts.repository.FactsRepository

object RepositoryFactory {

    fun createFactsRepository(context: Context) : FactsRepository {

        val factsApi = WebserviceUtil.instance.retrofit.create(Webservice::class.java)
        return FactsRepository(context, factsApi)
    }

}