package com.gkonstantakis.productsapp.products.models

data class UiProduct(
    var id: Int,
    var name: String,
    var price: String,
    var thumbnail: String,
    var image: String,
    var description: String?
)
