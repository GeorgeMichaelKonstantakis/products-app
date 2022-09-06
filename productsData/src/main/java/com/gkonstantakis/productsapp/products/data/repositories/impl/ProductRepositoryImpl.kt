package com.gkonstantakis.productsapp.products.data.repositories.impl

import android.util.Log
import com.gkonstantakis.productsapp.products.data.database.ProductDao
import com.gkonstantakis.productsapp.products.data.mappers.DatabaseMapper
import com.gkonstantakis.productsapp.products.data.mappers.NetworkMapper
import com.gkonstantakis.productsapp.products.data.models.Product
import com.gkonstantakis.productsapp.products.data.network.ProductNetworkService
import com.gkonstantakis.productsapp.products.data.repositories.ProductRepository
import com.gkonstantakis.productsapp.products.data.utils.Datastate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepositoryImpl(
    private val productNetworkService: ProductNetworkService,
    private val productDao: ProductDao,
    private val databaseMapper: DatabaseMapper,
    private val networkMapper: NetworkMapper
) : ProductRepository {

    override suspend fun getNetworkProducts(): Flow<Datastate<List<Product>>> = flow {
        emit(Datastate.Loading)
        try {
            val networkProducts = productNetworkService.get()
            if (networkProducts.isNullOrEmpty()) {
                emit(Datastate.Error("No products available."))
            } else {
                productDao.deleteProducts()
                val products = networkMapper.mapFromEntitiesList(networkProducts)
                val databaseProducts = databaseMapper.mapToEntitiesList(products)
                databaseProducts.forEach {
                    productDao.insertProduct(it)
                }
                emit(Datastate.SuccessNetworkGet(products.sortedBy { it.id }))
            }
        } catch (e: Exception) {
            Log.e("ProductRepository", "getNetworkProducts: $e")
            emit(Datastate.Error("NETWORK_ERROR"))
        }
    }

    override suspend fun getDatabaseProducts(): Flow<Datastate<List<Product>>> = flow {
        emit(Datastate.Loading)
        try {
            val databaseProducts = productDao.getProducts()
            if (databaseProducts.isNullOrEmpty()) {
                emit(Datastate.Error("DATABASE_ERROR"))
            } else {
                val products = databaseMapper.mapFromEntitiesList(databaseProducts)
                emit(Datastate.SuccessDatabaseGet(products.sortedBy { it.id }))
            }
        } catch (e: Exception) {
            Log.e("ProductRepository", "getDatabaseProducts: $e")
            emit(Datastate.Error("DATABASE_ERROR"))
        }
    }

    override suspend fun deleteProducts() {
        try {
            productDao.deleteProducts()
        } catch (e: Exception) {
            Log.e("ProductRepository", "deleteProducts: $e")
        }
    }
}