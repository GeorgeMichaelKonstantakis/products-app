package com.gkonstantakis.productsapp.products.ui

import com.gkonstantakis.productsapp.products.data.models.Product
import com.gkonstantakis.productsapp.products.mappers.UiMapper
import com.gkonstantakis.productsapp.products.ui.testdata.TestProducts
import org.junit.Before
import org.junit.Test

class MappersTest {

    private lateinit var products: List<Product>
    private lateinit var uiMapper: UiMapper

    @Before
    fun setup() {
        products = TestProducts().createTestProducts()
        uiMapper = UiMapper()
    }

    @Test
    fun uiMapping() {
        val uiProducts = uiMapper.mapToEntitiesList(products)
        val modelProducts = uiMapper.mapFromEntitiesList(uiProducts)
        products.forEachIndexed { index, product ->
            assert(product == modelProducts[index])
        }
    }

}