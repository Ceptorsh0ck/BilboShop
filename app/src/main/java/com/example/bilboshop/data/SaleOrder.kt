package com.example.bilboshop.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SaleOrder(
    val id: Int,
    val user: User,
    val date: String,
    val orderLines: MutableList<SaleOrderLine>,
): Parcelable
