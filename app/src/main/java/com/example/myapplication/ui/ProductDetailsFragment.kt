package com.example.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.transaction
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentProductDetailsBinding
import com.example.myapplication.model.ProductCard
import com.example.myapplication.viewmodels.ProductDetailsViewModel

class ProductDetailsFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var product: ProductCard
    private lateinit var viewModel: ProductDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        product = ProductDetailsFragmentArgs.fromBundle(requireArguments()).product
        val viewModelFactory = ProductDetailsViewModel.Factory(requireActivity().application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ProductDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding  = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_product_details, container, false)
        binding.product = product
        binding.button6.setOnClickListener {
            viewModel.addToBuy(product)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)
        NavigationUI.setupWithNavController(binding.mainToolbar, navController)
    }
}