package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ProductListByCategorieBinding
import com.example.myapplication.model.ProductHolder

class CategoriesCardListAdapter(val clickLListener: OnProductClickListener): ListAdapter<ProductHolder, CategoriesCardListAdapter.ViewHolder>(
    CategoriesCardDiffCallBacks()
){

    class ViewHolder(private val binding: ProductListByCategorieBinding):RecyclerView.ViewHolder(binding.root){

        private val adapter = ProductListAdapter()

        fun bind(item: ProductHolder){
            binding.category = item.category
            binding.productList.adapter = adapter
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
        val item = getItem(position)
        holder.bind(item)
    }
}

class CategoriesCardDiffCallBacks: DiffUtil.ItemCallback<ProductHolder>(){
    override fun areItemsTheSame(oldItem: ProductHolder, newItem: ProductHolder): Boolean {
        return oldItem.category == newItem.category
    }

    override fun areContentsTheSame(oldItem: ProductHolder, newItem: ProductHolder): Boolean {
        return oldItem == newItem
    }
}

class OnProductClickListener(val callBack: () -> Unit){
    fun onClick() = callBack()
}