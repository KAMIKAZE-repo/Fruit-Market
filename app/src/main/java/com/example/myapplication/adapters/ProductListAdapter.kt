package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ProductCardBinding
import com.example.myapplication.model.ProductCard

class ProductListAdapter: RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    var data = listOf<ProductCard>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    class ViewHolder(private val binding: ProductCardBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: ProductCard){
            binding.product = item
            //TODO("Send GET request to get the product related to provided ID")
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup):ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProductCardBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = data.size
}