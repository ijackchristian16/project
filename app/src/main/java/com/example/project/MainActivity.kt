package com.example.project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.project.api.DataManager
import com.example.project.api.ProductApi
import com.example.project.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        recyclerView = findViewById(R.id.recyclerView)
        CoroutineScope(Dispatchers.Main).launch {
            val productApi = ProductApi()
            val products = productApi.getProducts()
            productAdapter = ProductAdapter(products)
            storeToDB(products)
            recyclerView.adapter = productAdapter
        }
    }

    private fun storeToDB(products: List<Product>) {
        products.forEach {
            DataManager.setContext(this.applicationContext)
            DataManager.insertProduct(it)
        }
    }
}
