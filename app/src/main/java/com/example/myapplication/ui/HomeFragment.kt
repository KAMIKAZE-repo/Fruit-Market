package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.myapplication.R
import com.example.myapplication.adapters.CategoriesCardListAdapter
import com.example.myapplication.adapters.OnProductClickListener
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.viewmodels.HomeFragmentViewModel
import com.google.android.material.chip.Chip

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var categoriesAdapter: CategoriesCardListAdapter
    private lateinit var viewModel: HomeFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoriesAdapter = CategoriesCardListAdapter(OnProductClickListener(
            {
                product ->  viewModel.navigate(product)
            },
            {
                posHolder, posProduct -> viewModel.addFavorites(posHolder, posProduct)
            },
            {
                category ->  viewModel.navigateCategory(category)
            }
        ))
        viewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)
        binding.homeRecyclerView.adapter = categoriesAdapter
        binding.retry.setOnClickListener {
            viewModel.getData()
        }
        binding.lifecycleOwner = this
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
            categoriesAdapter.submitList(it)
        })

        viewModel.homeVisibility.observe(viewLifecycleOwner, {
            binding.visibilty = it
        })

        viewModel.navigateToDestination.observe(viewLifecycleOwner, {
            if(it != null){
                val mainNavController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
                mainNavController.navigate(NavHostFragmentDirections.actionNavHostFragmentToProductDetailsFragment(it))
                viewModel.navigationDone()
            }
        })

        viewModel.navigateToCategory.observe(viewLifecycleOwner, {
            if(it != null){
                val mainNavController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
                mainNavController.navigate(NavHostFragmentDirections.actionNavHostFragmentToCategoryProductsFragment(it))
                viewModel.navigationCategoryDone()
            }
        })

        //viewModel.getData()
    }
}