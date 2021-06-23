package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSignInBinding
import com.example.myapplication.viewmodels.SingInViewModel
import androidx.navigation.fragment.NavHostFragment.findNavController


class SignInFragment : Fragment() {

    private lateinit var viewModel: SingInViewModel
    private lateinit var binding: FragmentSignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SingInViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_sign_in, container, false)
        binding.textInputLayout.editText?.setOnEditorActionListener { _, actionId, _ ->
            when(actionId){
                EditorInfo.IME_ACTION_DONE -> {
                    viewModel.navigateToDestination()
                    true
                }
                else -> false
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigate.observe(viewLifecycleOwner, {
            if(it){
                findNavController(this).navigate(
                    SignInFragmentDirections.actionSignInFragment3ToVerifyPhoneNumberFragment(
                        binding.textInputLayout.editText?.text.toString().toInt()
                    )
                )
                viewModel.navigationDone()
            }
        })
    }
}