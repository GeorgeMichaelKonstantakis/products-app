package com.gkonstantakis.productsapp.products

import android.app.Application
import androidx.room.Room
import com.gkonstantakis.productsapp.products.data.database.ProductDao
import com.gkonstantakis.productsapp.products.data.database.ProductDatabase
import com.gkonstantakis.productsapp.products.data.mappers.DatabaseMapper
import com.gkonstantakis.productsapp.products.data.mappers.NetworkMapper
import com.gkonstantakis.productsapp.products.data.network.ProductNetworkService
import com.gkonstantakis.productsapp.products.data.repositories.ProductRepository
import com.gkonstantakis.productsapp.products.data.repositories.impl.ProductRepositoryImpl
import com.gkonstantakis.productsapp.products.data.utils.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductsApplication : Application() {
    lateinit var productDB: ProductDatabase
    lateinit var productDao: ProductDao
    lateinit var productNetworkService: ProductNetworkService
    lateinit var productRepository: ProductRepository

    override fun onCreate() {
        super.onCreate()

        productDB = Room
            .databaseBuilder(
                applicationContext,
                ProductDatabase::class.java,
                ProductDatabase.PRODUCT_DATABASE
            )
            .build()

        productDao = (productDB as ProductDatabase).productDao()

        productNetworkService =
            provideGsonBuilder()!!.let {
                provideNetwork(it)!!.build()!!.create(ProductNetworkService::class.java)
            }

        productRepository =
            ProductRepositoryImpl(
                productNetworkService,
                productDao, DatabaseMapper(), NetworkMapper()
            )

    }

    fun provideGsonBuilder(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    fun provideNetwork(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }
}