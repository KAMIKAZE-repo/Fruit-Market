package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ProductListByCategorieBinding
import com.example.myapplication.model.Category
import com.example.myapplication.model.ProductHolder

class CategoriesCardListAdapter(private val networkRequestCallBack: NetworkRequestCallBack):
    ListAdapter<ProductHolder, CategoriesCardListAdapter.ViewHolder>(CategoriesCardListDiffCallBack()) {

    inner class ViewHolder(private val binding: ProductListByCategorieBinding,
                     private val networkRequestCallBack: NetworkRequestCallBack):
        RecyclerView.ViewHolder(binding.root){

        private val adapter = ProductListAdapter(ProductNetworkCallBack{ posCategory, position ->
            networkRequestCallBack.senProductRequest(posCategory, position)
        }, adapterPosition)

        fun bind(item: ProductHolder, position: Int){
            binding.category = item.category
            //still in test phase
            binding.productList.adapter = adapter
            adapter.submitList(item.productsCards)

            //Testing UnderConstruction Pls Eb3ed
            networkRequestCallBack.sendRequest(item.category, position)

            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ProductListByCategorieBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, networkRequestCallBack)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position)
    }
}

class CategoriesCardListDiffCallBack: DiffUtil.ItemCallback<ProductHolder>(){
    override fun areItemsTheSame(oldItem: ProductHolder, newItem: ProductHolder): Boolean {
        return  oldItem.category == newItem.category
    }

    override fun areContentsTheSame(oldItem: ProductHolder, newItem: ProductHolder): Boolean {
       // return (oldItem.category == newItem.category) && (oldItem.products == newItem.products)
        return oldItem == newItem
    }
}

class NetworkRequestCallBack(
        val callBack: (category: Category, position: Int) -> Unit,
        val productCallBack: (posCategory: Int, position: Int) -> Unit
    ){
    fun sendRequest(category: Category, position: Int) = callBack(category, position)
    fun senProductRequest(posCategory: Int, position: Int) = productCallBack(posCategory, position)
}