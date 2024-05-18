package com.example.bilboshop.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bilboshop.MyApp
import com.example.bilboshop.data.Product
import com.example.bilboshop.data.Shop
import com.example.bilboshop.utils.Resource



class ProductsViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
class ProductsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is product Fragment"
    }
    val text: LiveData<String> = _text

    val items: LiveData<Resource<List<Product>>> get() = _items
    private val _items = MutableLiveData<Resource<List<Product>>>()

    fun loadProductsFromShop(shop: Shop) {
        if(shop == null) {
            _items.value = Resource.error("No se ha podido cargar los productos", null)
            return
        }else{
            _items.value = Resource.success(shop.products)
        }
    }



}