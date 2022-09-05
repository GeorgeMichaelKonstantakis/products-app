package com.gkonstantakis.productsapp.products.utils

class Utils {

    fun safeUrl(url: String): String{
        if (url.contains("http")) {
            return url.replace("http", "https")
        } else {
            return url
        }
    }
}