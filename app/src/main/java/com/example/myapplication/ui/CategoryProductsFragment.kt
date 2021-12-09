package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.OnItemClickListener
import com.example.myapplication.adapters.ProductListAdapter
import com.example.myapplication.databinding.FragmentCategoryProductsBinding
import com.example.myapplication.model.Category
import com.example.myapplication.viewmodels.CategoryProductViewModel

class CategoryProductsFragment : Fragment() {
    private val gridLayoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
    private val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    private lateinit var binding: FragmentCategoryProductsBinding
    private lateinit var adapter: ProductListAdapter
    private lateinit var viewModel: CategoryProductViewModel
    private lateinit var category: Category
    private var layoutManagerIndex = 1 // 1 means gridLayoutManager 2 means LinearLayoutManger//TODO:("should be in the viewModel to survive changes")
    private var sortOption = false //same goes here

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        category = CategoryProductsFragmentArgs.fromBundle(requireArguments()).category
        val factory = CategoryProductViewModel.Factory(requireActivity().application, category)
        viewModel = ViewModelProvider(this, factory).get(CategoryProductViewModel::class.java)
        adapter = ProductListAdapter(OnItemClickListener({
                product ->
        },{
                pos -> viewModel.addToFavorite(pos)
        }))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =  FragmentCategoryProductsBinding.inflate(layoutInflater, container, false)
        binding.productList.layoutManager = gridLayoutManager
        binding.productList.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.listProduct.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

        val navController = Navigation.findNavController(view)
        NavigationUI.setupWithNavController(binding.toolbar, navController)
        binding.toolbar.title = category.name

        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.sort -> {
                    viewModel.sortProduct(!sortOption)
                    sortOption = !sortOption
                    true
                }
                R.id.layout_manager -> {
                    binding.productList.layoutManager = if (layoutManagerIndex == 1 ) {
                        layoutManagerIndex = 2
                        linearLayoutManager
                    }else{
                        layoutManagerIndex = 1
                        gridLayoutManager
                    }
                    true
                }
                else -> false
            }
        }
    }
}