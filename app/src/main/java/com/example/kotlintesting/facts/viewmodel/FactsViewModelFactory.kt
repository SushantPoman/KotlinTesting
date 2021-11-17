package com.example.kotlintesting.facts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlintesting.facts.repository.FactsRepository

@Suppress("UNCHECKED_CAST")
class FactsViewModelFactory(
    private val repository: FactsRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FactsViewModel(repository) as T
    }
}