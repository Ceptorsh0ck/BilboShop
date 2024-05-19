package com.example.bilboshop.ui.cart

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bilboshop.R
import com.example.bilboshop.data.SaleOrder
import com.example.bilboshop.data.SaleOrderLine
import com.example.bilboshop.data.Shop
import com.example.bilboshop.databinding.OrderLineItemBinding
import com.example.bilboshop.databinding.ShopItemBinding

class SaleOrderAdapter(
    private val onClickListener: (SaleOrderLine) -> Unit,

): ListAdapter <SaleOrderLine, SaleOrderAdapter.SaleOrderViewHolder>(SaleOrderLineDiffCallback()) {
    private lateinit var recyclerView: RecyclerView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleOrderViewHolder {
        val binding = OrderLineItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SaleOrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SaleOrderViewHolder, position: Int) {
        val order = getItem(position)
        holder.bind(order)
        holder.itemView.setOnClickListener {
        }
    }
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }


    inner class SaleOrderViewHolder(private val binding: OrderLineItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(orderLine: SaleOrderLine) {
            binding.textProductNameCart.text = orderLine.product.name
            binding.textProductPriceCart2.text =  orderLine.product.price.toString() + " €"
            binding.textProductQtyCart.text = orderLine.quantity.toString()
            binding.productImage.setImageResource(R.drawable.productos)
            binding.totalPriceText.text = computeTotalPrice(orderLine)


        }
    }

    private fun computeTotalPrice(orderLine: SaleOrderLine): String {
        return (orderLine.product.price * orderLine.quantity).toString() + " €"
    }
   class SaleOrderLineDiffCallback : DiffUtil.ItemCallback<SaleOrderLine>() {
        override fun areItemsTheSame(oldItem: SaleOrderLine, newItem: SaleOrderLine): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SaleOrderLine, newItem: SaleOrderLine): Boolean {
            return oldItem == newItem
        }
    }

}