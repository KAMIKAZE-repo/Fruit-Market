package com.example.myapplication.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.database.ProductEntity
import com.example.myapplication.databinding.ProductBuyCardBinding

class ProductToBuyListAdapter(private val clickListenerCallBack: AmountClickListeners):
    ListAdapter<ProductEntity, ProductToBuyListAdapter.ViewHolder>(ProductBuyDiffCallBacks()) {

    class ViewHolder(val binding: ProductBuyCardBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(item: ProductEntity, clickListenerCallBack: AmountClickListeners){
            binding.product = item
            binding.clickListener = clickListenerCallBack
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup):ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: ProductBuyCardBinding = DataBindingUtil.inflate(layoutInflater, R.layout.product_buy_card, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListenerCallBack)
    }
}

class ProductBuyDiffCallBacks: DiffUtil.ItemCallback<ProductEntity>(){
    override fun areItemsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean {
        return oldItem.productId == newItem.productId
    }

    override fun areContentsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean {
        Log.i("TAG", "$oldItem, $newItem")
        return oldItem == newItem
    }
}

class AmountClickListeners(
    val amountCallBack: (value: Int, product: ProductEntity) -> Unit,
    val deleteCallBack: (product: ProductEntity) -> Unit
){
    fun onAmountClick(value: Int, product: ProductEntity) = amountCallBack(value, product)
    fun deleteProduct(product: ProductEntity) = deleteCallBack(product)
}