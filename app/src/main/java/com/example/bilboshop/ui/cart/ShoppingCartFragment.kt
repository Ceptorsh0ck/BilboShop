package com.example.bilboshop.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.bilboshop.R
import com.example.bilboshop.databinding.ActivityMainBinding
import com.example.bilboshop.databinding.FragmentShoppingCartBinding
import com.example.bilboshop.databinding.FragmentShopsBinding
import com.example.bilboshop.ui.MainActivity
import com.google.android.material.snackbar.Snackbar

class ShoppingCartFragment : Fragment() {

    private lateinit var binding: FragmentShoppingCartBinding

    private lateinit var mainActivityBinding: ActivityMainBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivityBinding = ActivityMainBinding.bind(requireActivity().findViewById(R.id.drawer_layout))

        mainActivityBinding.appBarMain.fab.hide()
    }

    override fun onDetach() {
        super.onDetach()
        mainActivityBinding.appBarMain.fab.show()
    }

}