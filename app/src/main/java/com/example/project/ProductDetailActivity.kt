package com.example.project

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project.model.ProductEntity
import com.example.project.database.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var productRepository: ProductRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details_activity)

        productRepository = ProductRepository(this.application.applicationContext)

        val productId = intent.getIntExtra("productId", -1)
        if (productId != -1) {
            loadProductDetails(productId)
        }
    }

    private fun loadProductDetails(productId: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            val product = productRepository.getProductById(productId)
            if (product != null) {
                // Update UI with product details
                updateUI(product)
            }
        }
    }

    private fun updateUI(product: ProductEntity) {
        val productImageView = findViewById<ImageView>(R.id.productImageView)
        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)
        val priceTextView = findViewById<TextView>(R.id.priceTextView)
        val discountTextView = findViewById<TextView>(R.id.discountTextView)
        val ratingTextView = findViewById<TextView>(R.id.ratingTextView)
        val stockTextView = findViewById<TextView>(R.id.stockTextView)
        val imagesRecyclerView = findViewById<RecyclerView>(R.id.imagesRecyclerView)

        Glide.with(this).load(product.thumbnail).into(productImageView)
        titleTextView.text = product.title
        descriptionTextView.text = product.description
        priceTextView.text = "Price: $${product.price}"
        discountTextView.text = "Discount: ${product.discountPercentage}%"
        ratingTextView.text = "Rating: ${product.rating}"
        stockTextView.text = "Stock: ${product.stock}"

        val imagesAdapter = ProductImagesAdapter(product.images)
        imagesRecyclerView.adapter = imagesAdapter
        imagesRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }
}
