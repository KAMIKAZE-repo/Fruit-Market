package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentNavHostBinding

class NavHostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding : FragmentNavHostBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_nav_host , container, false)
        val navController = Navigation.findNavController(requireActivity(), R.id.home_fragment_container)
        binding.bottomNavView.setupWithNavController(navController)
        return binding.root
    }
}