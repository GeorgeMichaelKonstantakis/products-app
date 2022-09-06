package com.gkonstantakis.productsapp.products.data.utils

sealed class Datastate<out R> {

    data class SuccessNetworkGet<out T>(val data: T) : Datastate<T>()

    data class SuccessDatabaseGet<out T>(val data: T) : Datastate<T>()

    data class Error(val message: String) : Datastate<Nothing>()

    object Loading : Datastate<Nothing>()
}
