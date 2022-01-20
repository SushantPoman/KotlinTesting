package com.example.kotlintesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintesting.databinding.ActivityMainBinding
import com.example.kotlintesting.facts.adapter.FactsAdapter
import com.example.kotlintesting.facts.model.FactsRowModel
import com.example.kotlintesting.facts.viewmodel.FactsViewModel
import com.example.kotlintesting.facts.viewmodel.FactsViewModelFactory
import com.example.kotlintesting.global.GlobalFunctions
import com.example.kotlintesting.global.Logs
import com.example.kotlintesting.global.RepositoryFactory
import com.example.kotlintesting.global.Resource


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: FactsViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var manager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        manager = LinearLayoutManager(this)

        val viewModelFactory = FactsViewModelFactory(RepositoryFactory.createFactsRepository(this))
        viewModel = ViewModelProvider(this, viewModelFactory).get(FactsViewModel::class.java)

        observeFactsLiveData()
        observeProgressDialog()

    }


    private fun observeFactsLiveData() {
        viewModel.getFactsData()

        if(GlobalFunctions.isInternetAvailable(this))
            viewModel.factsModel.observe(this, {
               if(it != null){
                   val dataList = it.rows
                   dataList.let {
                       binding.rvData.apply {
                           binding.progress.visibility = View.GONE
                           adapter = FactsAdapter(this@MainActivity, dataList)
                           layoutManager = manager
                       }
                   }
               } else
                   displayErrorMsg(getString(R.string.no_data_available))
            })
        else
            displayErrorMsg(getString(R.string.no_internet))

    }

    private fun displayErrorMsg(errMsg: String) {
        binding.progress.visibility = View.GONE
        binding.rvData.visibility = View.GONE
        binding.tvNoData.visibility = View.VISIBLE
        binding.tvNoData.text = errMsg

    }

    private fun observeProgressDialog() {
        viewModel.progressDialog?.observe(this, {
            if(it!!) binding.progress.visibility = View.VISIBLE
            else
                binding.progress.visibility = View.GONE
        })
    }


}