package com.example.bilboshop.ui.product

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bilboshop.R
import com.example.bilboshop.data.Product
import com.example.bilboshop.data.Shop
import com.example.bilboshop.databinding.FragmentProductsBinding
import com.example.bilboshop.utils.Resource

class ProductFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private lateinit var productAdapter: ProductAdapter

    private val productViewModel: ProductsViewModel by viewModels {
        ProductsViewModelFactory()
    }


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

private fun onProductClicked(product: Product) {
    val shop = arguments?.getParcelable<Shop>("shop")
    Log.i(ContentValues.TAG, "product clicked $product")


    val args = Bundle().apply {
        putParcelable("product", product)
        if (shop != null) {
            putParcelable("shop", shop)
        }
    }
    findNavController().navigate(R.id.action_nav_products_to_product_details, args)


    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val shop = arguments?.getParcelable<Shop>("shop")

        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        productAdapter = ProductAdapter (::onProductClicked)
        binding.productList.adapter = productAdapter
        val recyclerView = binding.productList
        recyclerView.layoutManager = LinearLayoutManager(context)
        binding.shopName.text = shop?.name
        if (shop != null) {
            productViewModel.loadProductsFromShop(shop)
        }


        productViewModel.items.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    Log.i(ContentValues.TAG, "sucess product ${it.data}")

                    productAdapter.submitList(it.data)
                }

                Resource.Status.ERROR -> {

                }

                Resource.Status.LOADING -> {

                }
            }
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

        return root

        }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}