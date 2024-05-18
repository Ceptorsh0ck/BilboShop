package com.example.bilboshop.ui.shops

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bilboshop.MyApp
import com.example.bilboshop.data.Shop
import com.example.bilboshop.utils.Resource



class ShopsViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShopsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ShopsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
class ShopsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is shops Fragment"
    }
    val text: LiveData<String> = _text

    val items: LiveData<Resource<List<Shop>>> get() = _items
    private val _items = MutableLiveData<Resource<List<Shop>>>()

    init {
        loadItems()
    }

    private fun loadItems() {
        val shops = MyApp.userPreferences.createDemoData()
        _items.value = Resource.success(shops)
    }



}