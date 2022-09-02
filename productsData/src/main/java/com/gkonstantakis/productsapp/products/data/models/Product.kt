package com.gkonstantakis.productsapp.products.data.models

data class Product(
    var id: Int,
    var name: String,
    var price: String,
    var thumbnail: String,
    var image: String,
    var description: String
)
