package com.example.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.adapters.CategoriesCardListAdapter
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.utils.categories
import com.google.android.material.chip.Chip

class HomeFragment : Fragment() {

    private lateinit var adapter: CategoriesCardListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = CategoriesCardListAdapter()
        adapter.data = categories
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        binding.chipsGrp.addView((layoutInflater.inflate(R.layout.custom_chip, binding.chipsGrp, false) as Chip)
            .apply {
                this.text = "choice_7"
            }
        )
        binding.homeRecyclerView.adapter = adapter
        return binding.root
    }
}