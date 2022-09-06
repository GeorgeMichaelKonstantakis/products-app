package com.gkonstantakis.productsapp.products.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gkonstantakis.productsapp.products.data.database.entities.DatabaseProduct

@Database(entities = [DatabaseProduct::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        const val PRODUCT_DATABASE: String = "product_db"
    }
}