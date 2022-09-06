package com.gkonstantakis.productsapp.products.ui.testdata

import com.gkonstantakis.productsapp.products.data.models.Product

class TestProducts {

    fun createTestProducts(): List<Product> {
        val testProducts = ArrayList<Product>()
        val noOfTestProducts = 20
        for (i in (1..noOfTestProducts)) {
            testProducts.add(
                testProduct(i)
            )
        }
        return testProducts
    }

    fun testProduct(id: Int): Product {
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return Product(
            id = id,
            name = generateRandomString(),
            price = generateRandomString(),
            thumbnail = generateRandomString(),
            image = generateRandomString(),
            description = generateRandomString()
        )
    }

    fun generateRandomString(): String {
        val STRING_LENGTH = 8
        val charsPool = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..STRING_LENGTH)
            .map { charsPool.random() }
            .joinToString("")
    }
}