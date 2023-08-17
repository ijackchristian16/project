package com.example.project.api

import android.content.Context
import com.example.project.model.ProductEntity
import com.example.project.database.ProductRepository
import com.example.project.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object DataManager {
    private var list = mutableListOf<Product>()
    private lateinit var productRepository: ProductRepository

    fun addProducts(productList: List<Product>) {
        list = productList as MutableList<Product>
    }

    fun getProductList(): MutableList<Product> {
        return list
    }

    fun getProductById(productId: Int): ProductEntity? {
        var product: ProductEntity? = null
        CoroutineScope(Dispatchers.IO).launch {
            product = productRepository.getProductById(productId)
        }
        return product
    }


    fun insertProduct(it: Product) {
        CoroutineScope(Dispatchers.IO).launch {
            productRepository.insertProduct(
                ProductEntity(
                    it.id,
                    it.title,
                    it.description,
                    it.price,
                    it.discountPercentage,
                    it.rating,
                    it.stock,
                    it.brand,
                    it.category,
                    it.thumbnail,
                    it.images
                )
            )
        }
    }

    fun setContext(context: Context) {
        productRepository = ProductRepository(context)
    }
}