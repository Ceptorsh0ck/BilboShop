package com.example.bilboshop.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bilboshop.MyApp
import com.example.bilboshop.data.Product
import com.example.bilboshop.data.SaleOrder
import com.example.bilboshop.data.SaleOrderLine
import com.example.bilboshop.data.Shop
import com.example.bilboshop.utils.Resource



class SaleOrderViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SaleOrderViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SaleOrderViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
class SaleOrderViewModel: ViewModel() {


    val items: LiveData<Resource<List<SaleOrderLine>>> get() = _items
    private val _items = MutableLiveData<Resource<List<SaleOrderLine>>>()

    fun loadOrderLinesFromOrder(order: SaleOrder) {
        if(order == null) {
            _items.value = Resource.error("No se ha podido cargar los productos", null)

            return
        }else{
            _items.value = Resource.success(order.orderLines)
        }
    }






}