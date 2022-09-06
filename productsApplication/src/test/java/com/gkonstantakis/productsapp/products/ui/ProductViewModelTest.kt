package com.gkonstantakis.productsapp.products.ui

import com.gkonstantakis.productsapp.products.ui.repositories.FakeProductRepository
import com.gkonstantakis.productsapp.products.ui.testdata.TestProducts
import com.gkonstantakis.productsapp.products.ui.viewmodels.FakeProductViewModel
import com.gkonstantakis.productsapp.products.ui.viewmodels.StateEvent
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ProductViewModelTest {

    private lateinit var productRepository: FakeProductRepository
    private lateinit var productViewModel: FakeProductViewModel

    @Before
    fun setup() {
        productRepository = FakeProductRepository()
        productRepository.setNewProducts(TestProducts().createTestProducts())
        productViewModel = FakeProductViewModel(productRepository)
    }

    @Test
    fun setStateEvent() {
        runBlocking {
            productViewModel.setStateEvent(StateEvent.GetNetworkProducts)
        }
    }
}