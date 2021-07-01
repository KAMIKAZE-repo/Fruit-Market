package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ProductListByCategorieBinding
import com.example.myapplication.model.ProductHolder

class CategoriesCardListAdapter: RecyclerView.Adapter<CategoriesCardListAdapter.ViewHolder>() {

    var data = listOf<ProductHolder>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(private val binding: ProductListByCategorieBinding):RecyclerView.ViewHolder(binding.root){

        private val adapter = ProductListAdapter()

        fun bind(item: ProductHolder){
            binding.category = item.category
            //still in test phase
            binding.productList.adapter = adapter
            //TODO("Change this you motherfucker!!!!")
            //adapter.data = HomeFragmentViewModel().getAllProducts(item.name)
            adapter.data = item.productsCards
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProductListByCategorieBinding.inflate(layoutInflater, parent, false)
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