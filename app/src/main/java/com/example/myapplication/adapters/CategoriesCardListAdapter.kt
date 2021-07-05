package com.example.myapplication.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ProductListByCategorieBinding
import com.example.myapplication.model.ProductCard
import com.example.myapplication.model.ProductHolder

class CategoriesCardListAdapter(val clickLListener: OnProductClickListener): ListAdapter<ProductHolder, CategoriesCardListAdapter.ViewHolder>(
    CategoriesCardDiffCallBacks()
){

    inner class ViewHolder(private val binding: ProductListByCategorieBinding):RecyclerView.ViewHolder(binding.root){

        private val adapter = ProductListAdapter(OnItemClickListener(
            {
                product -> clickLListener.onClick(product)
            },
            {
                pos -> clickLListener.onFavClick(layoutPosition, pos)
            }
        ))

        fun bind(item: ProductHolder){
            binding.category = item.category
            binding.productList.adapter = adapter
            adapter.submitList(item.productsCards)
            binding.executePendingBindings()
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ProductListByCategorieBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
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

class OnProductClickListener(
    val callBack: (product: ProductCard) -> Unit,
    val favCallBack: (posHolder: Int, posProduct: Int) -> Unit
){
    fun onClick(product: ProductCard) = callBack(product)
    fun onFavClick(posHolder: Int, posProduct: Int) = favCallBack(posHolder, posProduct)
}