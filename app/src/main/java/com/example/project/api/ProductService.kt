package com.example.project.api

import com.example.project.model.Product
import com.example.project.model.ProductsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ProductService {
    @GET("products") // Replace with your API endpoint
    suspend fun getProducts(): ProductsResponse
}