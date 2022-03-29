package com.example.kotlintesting.facts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlintesting.facts.model.FactsModel
import com.example.kotlintesting.facts.repository.FactsRepository
import com.example.kotlintesting.global.SingleLiveEvent
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FactsViewModel(private val repository: FactsRepository) : ViewModel() {


    var factsModel = MutableLiveData<FactsModel>()
    var progressDialog: SingleLiveEvent<Boolean>? = SingleLiveEvent()


    fun getFactsData() {

        repository.getFactsData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getDataListObserverRx())
    }

    private fun getDataListObserverRx(): Observer<FactsModel> {
        return object : Observer<FactsModel> {
            override fun onComplete(){
                progressDialog?.value = false
            }

            override fun onSubscribe(d: Disposable) {
                progressDialog?.value = true
            }

            override fun onNext(t: FactsModel) {
                factsModel.value = t
            }

            override fun onError(e: Throwable) {
                factsModel.value = null
                progressDialog?.value = false
            }
        }

    }

}