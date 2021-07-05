package com.example.myapplication.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ProductCardBinding
import com.example.myapplication.model.ProductCard

class ProductListAdapter(private val itemClickListener: OnItemClickListener):
    ListAdapter<ProductCard, ProductListAdapter.ViewHolder>(ProductListDiffCallBacks()) {

    class ViewHolder(private val binding: ProductCardBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: ProductCard, itemClickListener: OnItemClickListener, position: Int){
            binding.product = item
            binding.onclick = itemClickListener
            binding.favButton.setOnClickListener {
                itemClickListener.onFavClicked(position)
            }
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
        holder.bind(item, itemClickListener, position)
    }
}

class OnItemClickListener(
    val onClickCallBack: (product: ProductCard) -> Unit,
    val onFavoriteClickCallBack: (pos: Int) -> Unit
){
    fun onCLick(product: ProductCard) = onClickCallBack(product)
    fun onFavClicked(pos: Int) = onFavoriteClickCallBack(pos)
}

class ProductListDiffCallBacks: DiffUtil.ItemCallback<ProductCard>(){
    override fun areItemsTheSame(oldItem: ProductCard, newItem: ProductCard): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: ProductCard, newItem: ProductCard): Boolean {
        return oldItem == newItem
    }
}