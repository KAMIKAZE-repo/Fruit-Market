package com.example.myapplication.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.model.OnBoardData
import com.example.myapplication.ui.OnBoardModelFragment

class OnBoardingViewPagerAdapter(private val data: List<OnBoardData>, fa: FragmentActivity): FragmentStateAdapter(fa){

    override fun getItemCount() = data.size

    override fun createFragment(position: Int): Fragment {
        return OnBoardModelFragment.newInstance(data[position])
    }
}