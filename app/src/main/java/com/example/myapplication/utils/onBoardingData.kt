package com.example.myapplication.utils

import android.view.View
import com.example.myapplication.R
import com.example.myapplication.model.Category
import com.example.myapplication.model.OnBoard
import com.example.myapplication.model.OnBoardData
import com.example.myapplication.model.ProductCard

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
        20,
        ""
    ),
    Category(
        "Mixed Fruit Pack",
        "Fruit mix fresh pack",
        20,
        ""
    ),
    Category(
        "Stone Fruits",
        "Fresh Stone Fruits",
        20,
        ""
    ),
    Category(
        "Melons",
        "Fresh Melons Fruits",
        20,
    ""
    )
)


val products = mapOf(
    "Organic Fruits" to listOf(
        ProductCard(
            "url",
            "Strawberry",
            300.0,
            false
        ),
        ProductCard(
            "url",
            "Oranges",
            120.0,
            false
        ),
        ProductCard(
            "url",
            "Grapes",
            160.0,
            false
        )
    ),
    "Mixed Fruit Pack" to listOf(
        ProductCard(
            "url",
            "Multi Fruits Pack",
            350.0,
            false
        ),
        ProductCard(
            "url",
            "Paper Fruits Pack",
            230.0,
            false
        ),
        ProductCard(
            "url",
            "Tropicana",
            140.0,
            false
        )
    ),
    "Stone Fruits" to listOf(
        ProductCard(
            "url",
            "Nectarines",
            250.0,
            false
        ),
        ProductCard(
            "url",
            "Apricots",
            180.0,
            false
        ),
        ProductCard(
            "url",
            "Peaches",
            100.0,
            false
        )
    ),
    "Melons" to listOf(
        ProductCard(
            "url",
            "Nectarines",
            250.0,
            false
        ),
        ProductCard(
            "url",
            "Apricots",
            180.0,
            false
        ),
        ProductCard(
            "url",
            "Peaches",
            100.0,
            false
        )
    )
)

val chipsData = listOf(
    "Vegetables",
    "Fruits",
    "Dry Fruits"
)