package com.example.kotlintesting.facts.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlintesting.facts.model.FactsModel
import com.example.kotlintesting.facts.repository.FactsRepository
import com.example.kotlintesting.global.Resource

class FactsViewModel(private val repository: FactsRepository) : ViewModel() {


    private val _factsModel: MutableLiveData<Resource<FactsModel>> = MutableLiveData()
    val factsModel: LiveData<Resource<FactsModel>>
        get() = _factsModel


    @SuppressLint("CheckResult")
    fun fetchFacts() {

        repository.fetchFacts()
            .subscribe {
                _factsModel.postValue(it)
            }

    }


    /*private val _cityModel: MutableLiveData<FactsModel> = MutableLiveData()
    val cityModel: LiveData<FactsModel>
        get() = _cityModel

    fun fehData() {
        viewModelScope.launch {
            _cityModel.value = repository.getData()
        }
    }*/


}