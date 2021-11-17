package com.example.kotlintesting.facts

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


class FactsAdapter(val context: Context, private val list: List<FactsRowModel>) : RecyclerView.Adapter<FactsAdapter.RvHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RvHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.row_facts,
            parent,
        false))


    override fun onBindViewHolder(holder: RvHolder, position: Int) {
        holder.rowCityBinding.fact = list[position]
        /*Glide.with(holder.rowCityBinding.ivImg)
            .load(list[position].imageHref)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(R.drawable.ic_launcher_background)
            .into(holder.rowCityBinding.ivImg)*/
        holder.itemView.setOnClickListener {
            Toast.makeText(context, "${list[position].title}",Toast.LENGTH_LONG).show()
            Logs.i("Click", list[position].apply { "$title $imageHref" }.toString())
        }
    }

    override fun getItemCount(): Int = list.size

    inner class RvHolder(val rowCityBinding: RowFactsBinding) :RecyclerView.ViewHolder(rowCityBinding.root)

}