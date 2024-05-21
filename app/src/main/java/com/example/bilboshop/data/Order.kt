package com.example.bilboshop.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Order(
    val id: Int,
    val user: User,
    val date: String,
    val total: Double,
    val status: String
): Parcelable
