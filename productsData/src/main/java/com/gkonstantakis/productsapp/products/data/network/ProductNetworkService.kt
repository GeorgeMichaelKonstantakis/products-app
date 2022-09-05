package com.gkonstantakis.productsapp.products.data.network

import com.gkonstantakis.productsapp.products.data.network.entities.NetworkProduct
import retrofit2.http.GET
import retrofit2.http.Headers

interface ProductNetworkService {

    @GET("products")
    @Headers("Content-Type: application/json","Accept: application/json")
    suspend fun get(): List<NetworkProduct>
}