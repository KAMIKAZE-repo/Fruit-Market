package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.adapters.FavoritesListAdapter
import com.example.myapplication.adapters.OnAmountClickListener
import com.example.myapplication.databinding.FragmentFavoritesBinding
import com.example.myapplication.utils.favoriteProducts
import com.example.myapplication.viewmodels.FavoritesViewModel

class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var adapter: FavoritesListAdapter
    private lateinit var viewModel: FavoritesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = FavoritesListAdapter(OnAmountClickListener ({
            value, pos -> viewModel.updateAmount(value, pos)
        },{
            product, amount -> viewModel.addToBuy(product, amount)
        }))
        val viewModelFactory = FavoritesViewModel.Factory(requireActivity().application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(FavoritesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =  FragmentFavoritesBinding.inflate(layoutInflater,  container, false)
        binding.favList.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.listFavorite.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }
}