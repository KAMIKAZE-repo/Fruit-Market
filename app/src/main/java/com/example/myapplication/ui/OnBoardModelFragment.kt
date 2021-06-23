package com.example.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentOnBoardModelBinding
import com.example.myapplication.model.OnBoardData

private const val ARG_PARAM1 = "data"

class OnBoardModelFragment : Fragment() {

    private var data: OnBoardData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            data = it.getParcelable(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentOnBoardModelBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_on_board_model, container, false)
        binding.data = data
        binding.imageView.setImageResource(data!!.imgSource)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(data: OnBoardData) =
            OnBoardModelFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, data)
                }
            }
    }
}