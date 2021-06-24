package com.example.myapplication.utils

import android.view.View
import com.example.myapplication.R
import com.example.myapplication.model.Category
import com.example.myapplication.model.OnBoard
import com.example.myapplication.model.OnBoardData

val data = arrayListOf(
    OnBoardData(
        "E Shopping",
        "Explore top organic fruits & grab them",
        R.drawable.onboard_img_1
    ),
    OnBoardData(
        "Delivery on the way",
        "Get order by speed delivery",
        R.drawable.onboard_img_2
    ),
    OnBoardData(
        "Delivery Arrived",
        "Order is arrived at your place",
        R.drawable.onboard_img_3
    )
)

val states = arrayListOf(
    OnBoard(
        View.VISIBLE,
        dot1State = true,
        dot2State = false,
        dot3State = false,
        "Next"
    ),
    OnBoard(
        View.VISIBLE,
        dot1State = false,
        dot2State = true,
        dot3State = false,
        "Next"
    ),
    OnBoard(
        View.INVISIBLE,
        dot1State = false,
        dot2State = false,
        dot3State = true,
        "Get Started"
    ),
)


val categories = listOf(
    Category(
        "Organic Fruits",
        "Pick up from organic farms",
        20
    ),
    Category(
        "Mixed Fruit Pack",
        "Fruit mix fresh pack",
        20
    ),
    Category(
        "Stone Fruits",
        "Fresh Stone Fruits",
        20
    ),
    Category(
        "Melons",
        "Fresh Melons Fruits",
        20
    )
)