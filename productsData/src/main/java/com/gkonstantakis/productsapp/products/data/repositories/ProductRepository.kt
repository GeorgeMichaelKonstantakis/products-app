package com.gkonstantakis.productsapp.products.data.repositories

import com.gkonstantakis.productsapp.products.data.models.Product
import com.gkonstantakis.productsapp.products.data.utils.Datastate
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getNetworkProducts(): Flow<Datastate<List<Product>>>

    suspend fun getDatabaseProducts(): Flow<Datastate<List<Product>>>

    suspend fun deleteProducts()
}