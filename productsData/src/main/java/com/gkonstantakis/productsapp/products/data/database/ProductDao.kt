package com.gkonstantakis.productsapp.products.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gkonstantakis.productsapp.products.data.database.entities.DatabaseProduct

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(heroEntity: DatabaseProduct): Long

    @Query("SELECT * FROM PRODUCTS")
    suspend fun getProducts(): List<DatabaseProduct>

    @Query("DELETE FROM PRODUCTS")
    suspend fun deleteHeroes();
}