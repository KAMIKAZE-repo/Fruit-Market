package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.adapters.OnBoardingViewPagerAdapter
import com.example.myapplication.databinding.FragmentOnBoardingBinding
import com.example.myapplication.utils.data
import com.example.myapplication.viewmodels.OnBoardViewModel

class OnBoardingFragment : Fragment() {

    private lateinit var viewPagerAdapter: OnBoardingViewPagerAdapter
    private lateinit var viewModel: OnBoardViewModel
    private lateinit var binding: FragmentOnBoardingBinding

    private var doppelgangerPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            Log.i("TAG", "Selected position: $position")
            viewModel.changePage(position)
            viewModel.updateState(position)
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewPagerAdapter = OnBoardingViewPagerAdapter(data, this.requireActivity())
        viewModel = ViewModelProvider(this).get(OnBoardViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_on_boarding, container, false)
        binding.viewPager.adapter = viewPagerAdapter
        binding.lifecycleOwner = this

        binding.viewPager.registerOnPageChangeCallback(doppelgangerPageChangeCallback)

        binding.button.setOnClickListener {
            viewModel.changePage(binding.viewPager.currentItem+1)
        }

        binding.skip.setOnClickListener {
            viewModel.navigateToDestination()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner, {
            binding.state = it
        })

        viewModel.currentPos.observe(viewLifecycleOwner, {
            binding.viewPager.setCurrentItem(it, true)
        })

        viewModel.navigate.observe(viewLifecycleOwner, {
            if(it){
                findNavController(this).navigate(
                    OnBoardingFragmentDirections.actionOnBoardingFragmentToSignInFragment3()
                )
                viewModel.navigateToDestinationComplete()
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        binding.viewPager.adapter = null
    }
}