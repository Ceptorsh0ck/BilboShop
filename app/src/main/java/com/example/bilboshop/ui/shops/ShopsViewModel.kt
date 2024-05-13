package com.example.bilboshop.ui.shops

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShopsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is shops Fragment"
    }
    val text: LiveData<String> = _text
}