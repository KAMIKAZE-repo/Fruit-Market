package com.example.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.adapters.CategoriesCardListAdapter
import com.example.myapplication.adapters.ProductListAdapter
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.databinding.ProductCardBinding
import com.example.myapplication.databinding.ProductListByCategorieBinding
import com.example.myapplication.utils.categories
import com.example.myapplication.utils.products
import com.google.android.material.chip.Chip

class HomeFragment : Fragment() {

    private lateinit var categoriesAdapter: CategoriesCardListAdapter
    private lateinit var productAdapter: ProductListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoriesAdapter = CategoriesCardListAdapter()
        categoriesAdapter.data = categories

        productAdapter = ProductListAdapter()
        productAdapter.data = products
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val binding2 = ProductListByCategorieBinding.inflate(layoutInflater, container, false)
        binding.chipsGrp.addView((layoutInflater.inflate(R.layout.custom_chip, binding.chipsGrp, false) as Chip)
            .apply {
                this.text = "choice_7"
            }
        )
        binding.homeRecyclerView.adapter = categoriesAdapter
        //TODO("FIX THIS LITTLE BITCH")
        binding2.productList.adapter = productAdapter
        return binding.root
    }
}