package com.example.myapplication.model

data class Customer(
    val firstname: String,
    val lastname: String,
    val customer_url: String
)

data class Meta(
    val count: Int,
    val limit: Int,
    val page: Int,
    val next_url: String
)

data class CustomersContainer(
    val meta: Meta,
    val customers: List<Customer>
)