package com.example.bilboshop.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SaleOrderLine(
    val id: Int,
    val product: Product,
    val quantity: Int,
    val shopId: String
): Parcelable
