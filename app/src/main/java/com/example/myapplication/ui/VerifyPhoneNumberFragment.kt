package com.example.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentVerifyPhoneNumberBinding

class VerifyPhoneNumberFragment : Fragment() {
    private lateinit var binding: FragmentVerifyPhoneNumberBinding
    private var phoneNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        phoneNumber = VerifyPhoneNumberFragmentArgs.fromBundle(requireArguments()).phoneNumber
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentVerifyPhoneNumberBinding.inflate(layoutInflater, container, false)
        binding.button4.setOnClickListener{
            findNavController().navigate(VerifyPhoneNumberFragmentDirections.actionVerifyPhoneNumberFragmentToNavHostFragment())
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textInputEditText.setText(phoneNumber.toString())
    }
}