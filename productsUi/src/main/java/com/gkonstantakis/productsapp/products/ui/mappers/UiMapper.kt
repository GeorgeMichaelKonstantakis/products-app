package com.gkonstantakis.productsapp.products.ui.mappers

import com.gkonstantakis.productsapp.products.data.models.Product
import com.gkonstantakis.productsapp.products.ui.models.UiProduct

class UiMapper {
    fun mapFromEntity(entity: UiProduct): Product {
        return Product(
            id = entity.id,
            name = entity.name,
            price = entity.price,
            thumbnail = entity.thumbnail,
            image = entity.image,
            description = entity.description
        )
    }

    fun mapToEntity(entity: Product): UiProduct {
        return UiProduct(
            id = entity.id,
            name = entity.name,
            price = entity.price,
            thumbnail = entity.thumbnail,
            image = entity.image,
            description = entity.description
        )
    }

    fun mapFromEntitiesList(entities: List<UiProduct>): List<Product> {
        return entities.map {
            mapFromEntity(it)
        }
    }

    fun mapToEntitiesList(entities: List<Product>): List<UiProduct> {
        return entities.map {
            mapToEntity(it)
        }
    }
}