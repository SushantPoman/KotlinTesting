package com.example.kotlintesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlintesting.databinding.ActivityMainBinding
import com.example.kotlintesting.facts.FactsAdapter
import com.example.kotlintesting.facts.model.FactsRowModel
import com.example.kotlintesting.facts.viewmodel.FactsViewModel
import com.example.kotlintesting.facts.viewmodel.FactsViewModelFactory
import com.example.kotlintesting.global.Logs
import com.example.kotlintesting.global.RepositoryFactory
import com.example.kotlintesting.global.Resource


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: FactsViewModel
    private lateinit var mainActivityBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

//        Set empty adapter list to taking precautions of - No adapter attached; skipping layout
        mainActivityBinding.rvData.layoutManager = LinearLayoutManager(this)
        val list = ArrayList<FactsRowModel>()
        mainActivityBinding.rvData.adapter = FactsAdapter(this, list)


        val viewModelFactory = FactsViewModelFactory(RepositoryFactory.createFactsRepository())
        viewModel = ViewModelProvider(this, viewModelFactory).get(FactsViewModel::class.java)

//        viewModel.fetchData()
        viewModel.fetchFacts()


        this.viewModel.factsModel.observe(this, {
            when (it) {
                is Resource.Success -> {
                    mainActivityBinding.progress.visibility = View.GONE
                    val adapter = FactsAdapter(this, it.value.rows)
                    mainActivityBinding.rvData.adapter = adapter
                }
                is Resource.Failure -> {
                    mainActivityBinding.progress.visibility = View.GONE
                    mainActivityBinding.tvNoData.visibility = View.VISIBLE
                    Logs.e("Error", it.toString())
                }
                is Resource.Loading -> {
                    mainActivityBinding.progress.visibility = View.VISIBLE
                }
            }
        })


    }
}