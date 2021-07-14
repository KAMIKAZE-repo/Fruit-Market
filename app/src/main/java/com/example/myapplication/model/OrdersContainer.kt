package com.example.myapplication.model

data class OrdersContainer(val orders: List<OrderNetwork>)

data class OrderNetwork(
    val createdAt: String,
    val state: String,
    val order_url: String
){
    val orderId: Long
        get() {
            val index = order_url.lastIndexOf("/", order_url.lastIndex, true)
            return  order_url.substring(index+1).toLong()
        }
}

data class OrderDetails(
    val createdAt: String,
    val updateAt: String?,
    val state: String,
    val customer_url:String,
    val actions: Purchase?,
    val items_url: String,//"/shop/orders/7868/items/"
    val total: Double?
){
    val orderId: Long
        get(){
            var id = ""
            for(c in items_url)
            {
                if(c.code in 48..57) id += c
            }
            return id.toLong()
        }
}

data class Items(
    val order_url: String,
    val items: List<Item>
)

data class Item(
    val quantity: Int,
    val price: Double,
    val item_url: String,
    val product_url: String
)

data class Purchase(
    val purchase: PurchaseObject
)

data class PurchaseObject(
    val url: String,
    val method: String
)

data class ResponseItem(
    val quantity: Int,
    val price: Double,
    val item_url: String,
    val order_url: String,
    val product_url: String
)