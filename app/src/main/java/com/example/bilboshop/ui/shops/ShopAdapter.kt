package com.example.bilboshop.ui.shops

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bilboshop.R
import com.example.bilboshop.data.Shop
import com.example.bilboshop.databinding.ShopItemBinding

class ShopAdapter(
    private val onClickListener: (Shop) -> Unit,

): ListAdapter <Shop, ShopAdapter.ShopViewHolder>(ShopDiffCallback()) {
    private lateinit var recyclerView: RecyclerView
    private var fullShopList: List<Shop> = listOf()
    private var filteredShopList: List<Shop> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val binding = ShopItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShopViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        val shop = getItem(position)
        holder.bind(shop)
        holder.itemView.setOnClickListener {
            onClickListener(shop)
        }
    }
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }


    inner class ShopViewHolder(private val binding: ShopItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(shop: Shop) {
            binding.textShopName.text = shop.name
            binding.textShopCategory.text =  "Categoria: " + shop.category
            if(shop.category == "Electronica"){
                binding.shopImage.setImageResource(R.drawable.tiendaelectronica)
            }else if(shop.category == "Ropa"){
                binding.shopImage.setImageResource(R.drawable.tiendaropa)
            }else if(shop.category == "Alimentacion"){
                binding.shopImage.setImageResource(R.drawable.tiendaalimentacion)
            }


        }
    }

    fun submitShops(shops: List<Shop>) {
        fullShopList = shops
        filteredShopList = shops
        submitList(filteredShopList)
    }


    fun filterByCategory(category: String) {
        filteredShopList = if (category == "Todo") {
            fullShopList
        } else {
            Log.i("Filtro",category)
            fullShopList.filter { it.category.equals(category, ignoreCase = true) }
        }
        submitList(filteredShopList)
    }


    class ShopDiffCallback : DiffUtil.ItemCallback<Shop>() {
        override fun areItemsTheSame(oldItem: Shop, newItem: Shop): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Shop, newItem: Shop): Boolean {
            return oldItem == newItem
        }
    }

}