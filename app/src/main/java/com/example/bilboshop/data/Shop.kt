package com.example.bilboshop.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Shop(
    val id: Int,
    val name: String,
    val category: String,
    val address: String,
    val phone: String,
    val email: String,
    val website: String,
    val image: String,
    val description: String,
    val products: List<Product>
): Parcelable

