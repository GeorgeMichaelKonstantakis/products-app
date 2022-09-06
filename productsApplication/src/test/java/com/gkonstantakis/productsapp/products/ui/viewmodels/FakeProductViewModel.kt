package com.gkonstantakis.productsapp.products.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gkonstantakis.productsapp.products.data.models.Product
import com.gkonstantakis.productsapp.products.data.utils.Datastate
import com.gkonstantakis.productsapp.products.ui.repositories.FakeProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class FakeProductViewModel(
    private val productRepository: FakeProductRepository
) {
    private val scope = CoroutineScope(Dispatchers.Default)

    private val _dataState: MutableLiveData<Datastate<List<Product>>> =
        MutableLiveData()

    val dataState: LiveData<Datastate<List<Product>>>
        get() = _dataState

    fun setStateEvent(stateEvent: StateEvent) {
        scope.launch {
            when (stateEvent) {
                is StateEvent.GetNetworkProducts -> {
                    productRepository.getNetworkProducts().onEach { dataState ->
                        _dataState.value = dataState
                    }.launchIn(scope)
                }
                is StateEvent.GetDatabaseProducts -> {
                    productRepository.getDatabaseProducts().onEach { dataState ->
                        _dataState.value = dataState
                    }.launchIn(scope)
                }
            }
        }
    }

}

sealed class StateEvent {
    object GetNetworkProducts : StateEvent()

    object GetDatabaseProducts : StateEvent()
}