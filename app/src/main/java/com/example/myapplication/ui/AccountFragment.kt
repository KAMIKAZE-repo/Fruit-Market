package com.example.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =  FragmentAccountBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)
        binding.settings.setOnClickListener {
            navController.navigate(AccountFragmentDirections.actionAccountFragment2ToSettingsFragment())
        }

        binding.myFavorites.setOnClickListener {
            navController.navigate(AccountFragmentDirections.actionAccountFragment2ToNavHostFragment(R.id.favorites))
        }

        binding.myCart.setOnClickListener {
            navController.navigate(AccountFragmentDirections.actionAccountFragment2ToNavHostFragment(R.id.shoppingCartFragment))
        }

        binding.myOrders.setOnClickListener {
            navController.navigate(AccountFragmentDirections.actionAccountFragment2ToNavHostFragment(R.id.myOrdersFragment))
        }
    }
}