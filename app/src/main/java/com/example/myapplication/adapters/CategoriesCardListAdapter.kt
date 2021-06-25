package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ProductListByCategorieBinding
import com.example.myapplication.model.Category
import com.example.myapplication.viewmodels.HomeFragmentViewModel

class CategoriesCardListAdapter: RecyclerView.Adapter<CategoriesCardListAdapter.ViewHolder>() {

    var data = listOf<Category>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(private val binding: ProductListByCategorieBinding):RecyclerView.ViewHolder(binding.root){

        private val adapter = ProductListAdapter()

        fun bind(item: Category){
            binding.category = item
            //TODO("send get request to grab all products related to this category")
            //For test Only
            binding.productList.adapter = adapter
            adapter.data = HomeFragmentViewModel.getCategoryProducts(item.name)
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