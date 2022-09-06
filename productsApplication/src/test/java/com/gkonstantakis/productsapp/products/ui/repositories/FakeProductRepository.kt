package com.gkonstantakis.productsapp.products.ui.repositories

import com.gkonstantakis.productsapp.products.data.models.Product
import com.gkonstantakis.productsapp.products.data.repositories.ProductRepository
import com.gkonstantakis.productsapp.products.data.utils.Datastate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeProductRepository() : ProductRepository {

    var products = mutableListOf<Product>()

    override suspend fun getNetworkProducts(): Flow<Datastate<List<Product>>> = flow {
        emit((Datastate.SuccessNetworkGet(products.sortedBy { it.id })))
    }

    override suspend fun getDatabaseProducts(): Flow<Datastate<List<Product>>> = flow {
        emit((Datastate.SuccessDatabaseGet(products.sortedBy { it.id })))
    }

    override suspend fun deleteProducts() {
        products.clear()
    }

    fun setNewProducts(newProducts: List<Product>) {
        for (product in newProducts) {
            products.add(product)
        }
    }
}