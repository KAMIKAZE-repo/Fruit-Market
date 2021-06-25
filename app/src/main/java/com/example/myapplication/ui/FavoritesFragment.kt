package com.example.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import com.example.myapplication.R
import com.example.myapplication.adapters.FavoritesListAdapter
import com.example.myapplication.databinding.FragmentFavoritesBinding
import com.example.myapplication.model.ProductCard
import com.example.myapplication.utils.products

class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var adapter: FavoritesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = FavoritesListAdapter()
        adapter.data = products["Organic Fruits"]!!
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
}