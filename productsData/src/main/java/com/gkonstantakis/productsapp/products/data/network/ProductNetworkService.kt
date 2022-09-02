package com.gkonstantakis.productsapp.products.data.network

import com.gkonstantakis.productsapp.products.data.network.entities.NetworkProduct
import retrofit2.http.GET

interface ProductNetworkService {

    @GET("/")
    suspend fun get(): List<NetworkProduct>
}