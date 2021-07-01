package com.example.myapplication.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ProductCardBinding
import com.example.myapplication.model.ProductCard

class ProductListAdapter(private val networkCallBack:ProductNetworkCallBack, private val posCategory: Int):
    ListAdapter<ProductCard, ProductListAdapter.ViewHolder>(ProductListDiffCallBack()) {

    class ViewHolder(private val binding: ProductCardBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(
            item: ProductCard,
            networkCallBack: ProductNetworkCallBack,
            posCategory: Int,
            position: Int
        ){
            //TODO("Send GET request to get the product related to provided ID")
            binding.product = item
            networkCallBack.requestProduct(posCategory, position)
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
        val item = getItem(position)
        holder.bind(item, networkCallBack, posCategory, position)
    }
}

class ProductListDiffCallBack: DiffUtil.ItemCallback<ProductCard>(){
    override fun areItemsTheSame(oldItem: ProductCard, newItem: ProductCard): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: ProductCard, newItem: ProductCard): Boolean {
        val res  = oldItem == newItem
        Log.i("TAG", "$res")
        return res
    }
}

class ProductNetworkCallBack(val networkCall: (posCategory: Int, position: Int) -> Unit){
    fun requestProduct(posCategory: Int, position: Int) = networkCall(posCategory, position)
}