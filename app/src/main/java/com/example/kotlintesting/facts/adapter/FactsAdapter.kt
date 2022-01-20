package com.example.kotlintesting.facts.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintesting.R
import com.example.kotlintesting.databinding.RowFactsBinding
import com.example.kotlintesting.facts.model.FactsRowModel
import com.example.kotlintesting.global.Logs


class FactsAdapter(val context: Context, private val list: List<FactsRowModel>) : RecyclerView.Adapter<FactsAdapter.FactsViewHolder>() {

    inner class FactsViewHolder(val rowCityBinding: RowFactsBinding) :RecyclerView.ViewHolder(rowCityBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FactsViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.row_facts,
            parent,
        false))


    override fun onBindViewHolder(holder: FactsViewHolder, position: Int) {
        holder.rowCityBinding.fact = list[position]
        holder.itemView.setOnClickListener {
            Toast.makeText(context, "${list[position].title}",Toast.LENGTH_LONG).show()
            Logs.i("Click", list[position].apply { "$title $imageHref" }.toString())
        }
    }

    override fun getItemCount(): Int = list.size


}