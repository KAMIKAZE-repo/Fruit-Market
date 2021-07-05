package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FavoriteOrderLayoutBinding
import com.example.myapplication.model.FavoriteProduct

class FavoritesListAdapter(private val amountClickListener: OnAmountClickListener): ListAdapter<FavoriteProduct, FavoritesListAdapter.ViewHolder>(
    FavoriteListDiffCallBacks()
) {

    class ViewHolder(private val binding: FavoriteOrderLayoutBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(item: FavoriteProduct, amountClickListener: OnAmountClickListener, position: Int){
            binding.product = item.productCard
            binding.amount = item.amount
            binding.position = position
            binding.amountClickListener = amountClickListener
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup):ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FavoriteOrderLayoutBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, amountClickListener, position)
    }
}

class FavoriteListDiffCallBacks: DiffUtil.ItemCallback<FavoriteProduct>(){
    override fun areItemsTheSame(oldItem: FavoriteProduct, newItem: FavoriteProduct): Boolean {
        return oldItem.productCard == newItem.productCard
    }

    override fun areContentsTheSame(oldItem: FavoriteProduct, newItem: FavoriteProduct): Boolean {
        return oldItem == newItem
    }
}

class OnAmountClickListener(val amountCallBack: (value: Int, position: Int) -> Unit){
    fun onCLick(value: Int, pos: Int) = amountCallBack(value, pos)
}