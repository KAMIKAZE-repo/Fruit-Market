package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ProductCardBinding
import com.example.myapplication.model.Product
import com.example.myapplication.model.ProductCard
import com.example.myapplication.viewmodels.HomeFragmentViewModel

class ProductListAdapter: RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    var data = listOf<Product>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    class ViewHolder(private val binding: ProductCardBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: Product){
            //TODO("Send GET request to get the product related to provided ID")
            val index = item.productUrl.lastIndexOf("/", item.productUrl.lastIndex, true)
            val id = item.productUrl.substring(index+1)
            binding.product = HomeFragmentViewModel().getProductInfo(id.toInt())
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