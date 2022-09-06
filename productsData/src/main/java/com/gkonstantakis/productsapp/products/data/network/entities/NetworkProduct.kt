package com.gkonstantakis.productsapp.products.data.network.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NetworkProduct(
    @SerializedName("Id")
    @Expose
    var id: Int,

    @SerializedName("Name")
    @Expose
    var name: String,

    @SerializedName("Price")
    @Expose
    var price: String,

    @SerializedName("Thumbnail")
    @Expose
    var thumbnail: String,

    @SerializedName("Image")
    @Expose
    var image: String,

    @SerializedName("Description")
    @Expose
    var description: String?
)
