package com.example.bilboshop.ui.shops

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bilboshop.R
import com.example.bilboshop.data.Shop
import com.example.bilboshop.databinding.FragmentShopsBinding
import com.example.bilboshop.ui.gallery.GalleryFragment
import com.example.bilboshop.ui.product.ProductFragment
import com.example.bilboshop.utils.Resource

class ShopsFragment : Fragment() {

    private var _binding: FragmentShopsBinding? = null
    private lateinit var shopAdapter: ShopAdapter

    private val shopsViewModel: ShopsViewModel by viewModels {
        ShopsViewModelFactory()
    }


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private fun onShopClicked(shop: Shop) {
        val args = Bundle().apply {
            putParcelable("shop", shop)
        }
        findNavController().navigate(R.id.action_nav_shops_to_nav_products, args)



    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentShopsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        shopAdapter = ShopAdapter (::onShopClicked)
        binding.shopList.adapter = shopAdapter
        val recyclerView = binding.shopList
        recyclerView.layoutManager = LinearLayoutManager(context)



        shopsViewModel.items.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    Log.i(ContentValues.TAG, "sucess shop ${it.data}")

                    shopAdapter.submitList(it.data)
                }

                Resource.Status.ERROR -> {

                }

                Resource.Status.LOADING -> {

                }
            }
        }

        return root

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}