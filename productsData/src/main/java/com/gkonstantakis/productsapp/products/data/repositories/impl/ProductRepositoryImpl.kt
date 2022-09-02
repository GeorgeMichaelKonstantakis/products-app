package com.gkonstantakis.productsapp.products.data.repositories.impl

import com.gkonstantakis.productsapp.products.data.database.ProductDao
import com.gkonstantakis.productsapp.products.data.mappers.DatabaseMapper
import com.gkonstantakis.productsapp.products.data.mappers.NetworkMapper
import com.gkonstantakis.productsapp.products.data.models.Product
import com.gkonstantakis.productsapp.products.data.network.ProductNetworkService
import com.gkonstantakis.productsapp.products.data.repositories.ProductRepository
import com.gkonstantakis.productsapp.products.data.utils.Datastate
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImpl(
    private val productNetworkService: ProductNetworkService,
    private val productDao: ProductDao,
    private val databaseMapper: DatabaseMapper,
    private val networkMapper: NetworkMapper
) : ProductRepository {
    override suspend fun getNetworkProducts(): Flow<Datastate<List<Product>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getDatabaseProducts(): Flow<Datastate<List<Product>>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProducts() {
        TODO("Not yet implemented")
    }
}