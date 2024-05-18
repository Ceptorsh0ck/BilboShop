package com.example.bilboshop.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val productVariants: List<Product> = emptyList()

): Parcelable