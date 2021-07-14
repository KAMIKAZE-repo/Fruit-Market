package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.adapters.AmountClickListeners
import com.example.myapplication.adapters.ProductToBuyListAdapter
import com.example.myapplication.databinding.FragmentSHoppingCartBinding
import com.example.myapplication.viewmodels.ShoppingCartViewModel

class ShoppingCartFragment : Fragment() {
    private lateinit var adapter: ProductToBuyListAdapter
    private lateinit var binding: FragmentSHoppingCartBinding
    private lateinit var viewModel: ShoppingCartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ProductToBuyListAdapter(AmountClickListeners ({
            value, product -> viewModel.updateAmount(value, product)
        },{
            product ->  viewModel.deleteProduct(product)
        }))
        val viewModelFactory = ShoppingCartViewModel.Factory(requireActivity().application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ShoppingCartViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(layoutInflater, R.layout.fragment_s_hopping_cart, container, false)
        binding.lifecycleOwner = this
        binding.productList.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.productList.observe(viewLifecycleOwner, {
            adapter.submitList(it)
            viewModel.updateTotalPrice()
        })

        binding.placeOrders.setOnClickListener {
            viewModel.placeAllOrders()
            //Log.i("TAG", "Under Construction")
        }

        viewModel.totalPrice.observe(viewLifecycleOwner, {
            binding.textView15.text = "Total - $ $it"
        })
    }
}