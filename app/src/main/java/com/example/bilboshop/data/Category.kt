package com.example.bilboshop.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id: Int,
    val name: String,
    val image: Int
): Parcelable
