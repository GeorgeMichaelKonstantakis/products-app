package com.gkonstantakis.productsapp.products.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkonstantakis.productsapp.products.data.models.Product
import com.gkonstantakis.productsapp.products.data.repositories.ProductRepository
import com.gkonstantakis.productsapp.products.data.utils.Datastate
import com.gkonstantakis.productsapp.products.mappers.UiMapper
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ProductsViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _dataState: MutableLiveData<Datastate<List<Product>>> =
        MutableLiveData()

    val dataState: LiveData<Datastate<List<Product>>>
        get() = _dataState

    fun setStateEvent(stateEvent: StateEvent) {
        viewModelScope.launch {
            when (stateEvent) {
                is StateEvent.GetNetworkProducts -> {
                    productRepository.getNetworkProducts().onEach { dataState ->
                        _dataState.value = dataState
                    }.launchIn(viewModelScope)
                }
                is StateEvent.GetDatabaseProducts -> {
                    productRepository.getDatabaseProducts().onEach { dataState ->
                        _dataState.value = dataState
                    }.launchIn(viewModelScope)
                }
            }
        }
    }

}

sealed class StateEvent {
    object GetNetworkProducts : StateEvent()

    object GetDatabaseProducts : StateEvent()
}