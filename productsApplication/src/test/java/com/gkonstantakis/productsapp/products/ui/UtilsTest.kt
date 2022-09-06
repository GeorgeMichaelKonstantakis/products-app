package com.gkonstantakis.productsapp.products.ui

import com.gkonstantakis.productsapp.products.utils.Utils
import org.junit.Before
import org.junit.Test

class UtilsTest {

    private lateinit var utils: Utils

    @Before
    fun setup() {
       utils = Utils()
    }

    @Test
    fun safeUrlTest() {
        val url = "httpttttttttttt"
        val newUrl = utils.safeUrl(url)
        assert(newUrl == "httpsttttttttttt")
    }
}