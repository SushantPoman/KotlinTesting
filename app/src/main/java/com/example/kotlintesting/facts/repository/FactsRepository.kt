package com.example.kotlintesting.facts.repository

import com.example.kotlintesting.api.WebserviceApi
import com.example.kotlintesting.facts.model.FactsModel
import com.example.kotlintesting.global.Resource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FactsRepository(private val webserviceApi: WebserviceApi) {

    /*suspend fun getData(): Resource<FactsModel> {
        return withContext(Dispatchers.IO){
            try {
                Thread.sleep(3000)
                val jsonData = "{\"title\":\"About Canada\",\"rows\":[{\"title\":\"Beavers\",\"description\":\"Beavers build dams and lodges using tree branches, vegetation, rocks and mud; they chew down trees for building material. Dams impound water and lodges serve as shelters.\",\"imageHref\":\"http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg\"},{\"title\":\"Flag\",\"description\":\"null\",\"imageHref\":\"http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg\"},{\"title\":\"Transportation\",\"description\":\"The Department of Transport was created in 1935 by the government of William Lyon Mackenzie King in recognition of the changing transportation environment in Canada at the time.\",\"imageHref\":\"http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg\"},{\"title\":\"Hockey Night in Canada\",\"description\":\"Until the 1990s, there was only one game televised each Saturday night in any particular locality and up to 1968, regular season games were still not broadcast in their entirety.\",\"imageHref\":\"http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg\"}]}"
                val cityModel = Gson().fromJson(jsonData, FactsModel::class.java)
                Resource.Success(cityModel)
            } catch (e: Exception) {
                Resource.Failure("Exception: $e")
            }
        }
    }*/

    fun fetchFacts() : Observable<Resource<FactsModel>> {
        return Observable.create { emitter ->

            webserviceApi.getFacts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {
                    if (it.body() != null) {
//                        val cityModel = Gson().fromJson(it.body()!!, CityModel::class.java)
                        emitter.onNext(Resource.Success(it.body()!!))
                    }
                }, {
                    it.printStackTrace()
                    Resource.Failure(it.printStackTrace().toString())
                })
        }
    }

}