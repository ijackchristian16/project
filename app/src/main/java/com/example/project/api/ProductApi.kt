package com.example.project.api

import android.util.Log
import com.example.project.model.Product
import com.example.project.model.ProductsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductApi {

    private val productService: ProductService = RetrofitClient.retrofit.create(ProductService::class.java)

    suspend fun getProducts(): List<Product> = withContext(Dispatchers.IO) {
        val response: ProductsResponse = productService.getProducts()

        if (response.products.isNotEmpty()) {
            return@withContext response?.products ?: emptyList()
        } else {
            throw Exception("Failed to fetch products")
        }
    }
}