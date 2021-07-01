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
import com.example.myapplication.adapters.CategoriesCardListAdapter
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.utils.categories
import com.example.myapplication.viewmodels.HomeFragmentViewModel
import com.google.android.material.chip.Chip

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var categoriesAdapter: CategoriesCardListAdapter
    private lateinit var viewModel: HomeFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoriesAdapter = CategoriesCardListAdapter()
        viewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)
        binding.homeRecyclerView.adapter = categoriesAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.chips.observe(viewLifecycleOwner, {
            for(i in it){
                binding.chipsGrp.addView((layoutInflater.inflate(R.layout.custom_chip, binding.chipsGrp, false) as Chip)
                    .apply {
                        this.text = i
                    }
                )
            }
        })//Creation des chips

        viewModel.productHolderList.observe(viewLifecycleOwner, {
            categoriesAdapter.data = it
        })

        viewModel.homeVisibility.observe(viewLifecycleOwner, {
            binding.visibilty = it
        })
    }
}