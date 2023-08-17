package com.example.project.database

import android.content.Context
import com.example.project.model.ProductEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepository (context: Context){
    private val productDao: ProductDao = ProductDatabase.getDatabase(context).productDao()

    suspend fun insertProduct(product: ProductEntity) {
        withContext(Dispatchers.IO) {
            productDao.insertProduct(product)
        }
    }

    suspend fun getProductById(productId: Int): ProductEntity? {
        return withContext(Dispatchers.IO) {
            productDao.getProductById(productId)
        }
    }
}
