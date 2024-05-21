package com.example.bilboshop.ui.cart

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bilboshop.MyApp
import com.example.bilboshop.R
import com.example.bilboshop.data.Order
import com.example.bilboshop.data.SaleOrder
import com.example.bilboshop.data.SaleOrderLine
import com.example.bilboshop.databinding.ActivityMainBinding
import com.example.bilboshop.databinding.FragmentShoppingCartBinding
import com.example.bilboshop.databinding.FragmentShopsBinding
import com.example.bilboshop.ui.MainActivity
import com.example.bilboshop.utils.Resource
import com.google.android.material.snackbar.Snackbar

class ShoppingCartFragment : Fragment() {

    private lateinit var binding: FragmentShoppingCartBinding
    private lateinit var salesOrderLineAdapter: SaleOrderAdapter
    private lateinit var mainActivityBinding: ActivityMainBinding

    private val saleOrderViewModel: SaleOrderViewModel by viewModels {
        SaleOrderViewModelFactory()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        salesOrderLineAdapter = SaleOrderAdapter(::onSaleOrderLineClicked)
        val recyclerView = binding.cartList
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = salesOrderLineAdapter
        Log.i(ContentValues.TAG, "user sale order ${MyApp.userPreferences.userSaleOrder}")
        saleOrderViewModel.loadOrderLinesFromOrder(MyApp.userPreferences.userSaleOrder)


        saleOrderViewModel.items.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    Log.i(ContentValues.TAG, "sucess product ${it.data}")

                    salesOrderLineAdapter.submitList(it.data)
                }

                Resource.Status.ERROR -> {

                }

                Resource.Status.LOADING -> {

                }
            }
        }

        binding.button3.setOnClickListener() {
            view?.let { it1 ->
                Snackbar.make(it1, "Pedido realizado", Snackbar.LENGTH_LONG)
                    .show()
            }

            var cart = MyApp.userPreferences.userSaleOrder

            var price = 0.0
            for(order: SaleOrderLine in cart.orderLines) {
                price += order.quantity * order.product.price
            }

            MyApp.userPreferences.userAllOrders.add(Order(cart.id,cart.user,cart.date,price,"Preparando"))
            MyApp.userPreferences.userSaleOrder = SaleOrder(cart.id+1,cart.user,"2024-01-10",
                mutableListOf()
            )

            mainActivityBinding.appBarMain.fab.show()
            findNavController().navigate(R.id.nav_shops)
        }

        computeTotalPrice()





        return binding.root
    }

    private fun computeTotalPrice() {
        var totalPriceOrder = 0.0
        for (saleOrderLine in MyApp.userPreferences.userSaleOrder.orderLines) {
            totalPriceOrder += saleOrderLine.product.price * saleOrderLine.quantity
        }
        binding.totalOrderPrice.text = totalPriceOrder.toString() + " €"
    }
    private fun onSaleOrderLineClicked(saleOrderLine:SaleOrderLine) {
        Snackbar.make(binding.root, "SaleOrderLine clicked", Snackbar.LENGTH_LONG).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivityBinding = ActivityMainBinding.bind(requireActivity().findViewById(R.id.drawer_layout))

        mainActivityBinding.appBarMain.fab.hide()
    }

    override fun onDetach() {
        super.onDetach()
        //findNavController().popBackStack()
        mainActivityBinding.appBarMain.fab.show()
    }

}
