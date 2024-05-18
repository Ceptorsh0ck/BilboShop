package com.example.bilboshop.ui.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bilboshop.R
import com.example.bilboshop.data.Product
import com.example.bilboshop.databinding.ProductItemBinding

class ProductAdapter(
    private val onClickListener: (Product) -> Unit,

    ): ListAdapter <Product, ProductAdapter.ProductViewHolder>(ProductDiffCallback()) {
    private lateinit var recyclerView: RecyclerView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
        holder.itemView.setOnClickListener {
            onClickListener(product)
        }
    }
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }


    inner class ProductViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.textProductName.text = product.name
            binding.textProductPrice.text =  "Precio: " + product.price.toString() + " €"
            binding.productImage.setImageResource(R.drawable.productos)



        }
    }
   class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

}