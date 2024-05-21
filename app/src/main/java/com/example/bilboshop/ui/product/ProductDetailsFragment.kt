package com.example.bilboshop.ui.product

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bilboshop.MyApp
import com.example.bilboshop.R
import com.example.bilboshop.data.Product
import com.example.bilboshop.data.SaleOrderLine
import com.example.bilboshop.data.Shop
import com.example.bilboshop.databinding.FragmentProductsDetailsBinding
import com.example.bilboshop.ui.orders.OrderFragment
import com.google.android.material.snackbar.Snackbar

class ProductDetailsFragment : Fragment() {

    private var _binding: FragmentProductsDetailsBinding? = null
    private lateinit var productAdapter: ProductAdapter

    private val productViewModel: ProductsViewModel by viewModels {
        ProductsViewModelFactory()
    }


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private fun onProductClicked(product: Product) {
        val newFragment = OrderFragment()
        val args = Bundle()
        args.putParcelable("product", product)
        newFragment.arguments = args
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.shopList, newFragment)

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val shop = arguments?.getParcelable<Shop>("shop")
        val product = arguments?.getParcelable<Product>("product")

        _binding = FragmentProductsDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        productAdapter = ProductAdapter (::onProductClicked)
        binding.shopName.text = shop?.name
        if (product != null) {
            setProductDetails(product)
        }

        if (shop != null) {
            if(shop.category == "Electronica"){
                binding.shopImageBackground.setImageResource(R.drawable.tiendaelectronica)
            }else if(shop.category == "Ropa"){
                binding.shopImageBackground.setImageResource(R.drawable.tiendaropa)
            }else if(shop.category == "Alimentacion"){
                binding.shopImageBackground.setImageResource(R.drawable.tiendaalimentacion)
            }
        }

        val numberPicker: NumberPicker = binding.productQty
        numberPicker.minValue = 1
        numberPicker.maxValue = 99
        numberPicker.value = 1
        numberPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            Log.i(ContentValues.TAG, "value changed $newVal")
        }

        binding.addToCart.setOnClickListener {
            val saleOrderLine = SaleOrderLine(
                0,
                product!!,
                binding.productQty.value,
                shop!!.name
            )
            MyApp.userPreferences.userSaleOrder?.let {saleOrder ->
            saleOrder.orderLines.add(saleOrderLine)

            }
            view?.let { it1 ->
                Snackbar.make(it1, product.name + " añadido al carrito.", Snackbar.LENGTH_LONG)
                    .show()
            }

            Log.i(ContentValues.TAG, "saleOrderLine ${MyApp.userPreferences.userSaleOrder}")





        }



        return root

    }


    private fun setProductDetails(product: Product) {
        binding.textProductNameDetails.text = product.name
        binding.productDescription.text = product.description
        binding.productPrice.text = product.price.toString() + " €"
        binding.productDetailsImage.setImageResource(R.drawable.productos)

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}