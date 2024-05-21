package com.example.bilboshop.ui.shops

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bilboshop.MyApp
import com.example.bilboshop.data.Category
import com.example.bilboshop.data.Shop
import com.example.bilboshop.utils.Resource



class CategoriesViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoriesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CategoriesViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
class CategoriesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is shops Fragment"
    }
    val text: LiveData<String> = _text

    val items: LiveData<Resource<List<Category>>> get() = _items
    private val _items = MutableLiveData<Resource<List<Category>>>()

    init {
        loadItems()
    }

    private fun loadItems() {
        val categories = MyApp.userPreferences.categoryList
        _items.value = Resource.success(categories)
    }



}