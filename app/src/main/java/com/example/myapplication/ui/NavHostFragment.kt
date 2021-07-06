package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentNavHostBinding

class NavHostFragment : Fragment() {
    private lateinit var binding : FragmentNavHostBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_nav_host , container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val host: NavHostFragment = childFragmentManager.findFragmentById(R.id.home_fragment_container) as NavHostFragment
        binding.bottomNavView.setupWithNavController(host.navController)
        NavigationUI.setupWithNavController(binding.toolbar, host.navController)
        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.account -> {
                    Navigation.findNavController(view).navigate(NavHostFragmentDirections.actionNavHostFragmentToAccountFragment2())
                    true
                }
                else -> false
            }
        }
    }
}