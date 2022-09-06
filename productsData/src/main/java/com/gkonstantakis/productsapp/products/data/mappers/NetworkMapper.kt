package com.gkonstantakis.productsapp.products.data.mappers

import com.gkonstantakis.productsapp.products.data.models.Product
import com.gkonstantakis.productsapp.products.data.network.entities.NetworkProduct

class NetworkMapper {
    fun mapFromEntity(entity: NetworkProduct): Product {
        return Product(
            id = entity.id,
            name = entity.name,
            price = entity.price,
            thumbnail = entity.thumbnail,
            image = entity.image,
            description = entity.description
        )
    }

    fun mapToEntity(entity: Product): NetworkProduct {
        return NetworkProduct(
            id = entity.id,
            name = entity.name,
            price = entity.price,
            thumbnail = entity.thumbnail,
            image = entity.image,
            description = entity.description
        )
    }

    fun mapFromEntitiesList(entities: List<NetworkProduct>): List<Product> {
        return entities.map {
            mapFromEntity(it)
        }
    }
}