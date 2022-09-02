package com.gkonstantakis.productsapp.products.data.mappers

import com.gkonstantakis.productsapp.products.data.database.entities.DatabaseProduct
import com.gkonstantakis.productsapp.products.data.models.Product

class DatabaseMapper {
    fun mapFromEntity(entity: DatabaseProduct): Product {
        return Product(
            id = entity.id,
            name = entity.name,
            price = entity.price,
            thumbnail = entity.thumbnail,
            image = entity.image,
            description = entity.description
        )
    }

    fun mapToEntity(entity: Product): DatabaseProduct {
        return DatabaseProduct(
            id = entity.id,
            name = entity.name,
            price = entity.price,
            thumbnail = entity.thumbnail,
            image = entity.image,
            description = entity.description
        )
    }

    fun mapFromEntitiesList(entities: List<DatabaseProduct>): List<Product> {
        return entities.map {
            mapFromEntity(it)
        }
    }

    fun mapToEntitiesList(entities: List<Product>): List<DatabaseProduct> {
        return entities.map {
            mapToEntity(it)
        }
    }
}