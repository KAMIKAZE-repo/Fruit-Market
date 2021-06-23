package com.example.myapplication.model

data class OnBoard(
    val skipVisibility: Int,
    val dot1State: Boolean,
    val dot2State: Boolean,
    val dot3State: Boolean,
    val buttonText: String
)