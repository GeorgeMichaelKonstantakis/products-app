package com.gkonstantakis.productsapp.products.ui

import android.util.Log
import com.gkonstantakis.productsapp.products.ui.repositories.FakeProductRepository
import com.gkonstantakis.productsapp.products.ui.testdata.TestProducts
import com.gkonstantakis.productsapp.products.viewmodels.ProductsViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class ProductRepositoryTests {

    private lateinit var productRepository: FakeProductRepository

    @Before
    fun setup() {
        productRepository = FakeProductRepository()
        productRepository.setNewProducts(TestProducts().createTestProducts())
    }

    @Test
    fun getNetworkProductsTest() {
        runBlocking {
            productRepository.getNetworkProducts().onEach {
                Log.e("getNetworkProductsTest", "Success")
            }
        }
    }

    @Test
    fun getDatabaseProductsTest() {
        runBlocking {
            productRepository.getDatabaseProducts().onEach {
                Log.e("getNetworkProductsTest", "Success")
            }
        }
    }

    @Test
    fun deleteProductsTest() {
        runBlocking {
            productRepository.deleteProducts()
        }
    }
}