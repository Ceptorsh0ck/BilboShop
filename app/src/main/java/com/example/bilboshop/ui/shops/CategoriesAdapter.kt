package com.example.bilboshop.ui.shops

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bilboshop.data.Category
import com.example.bilboshop.databinding.CategoryItemBinding

class CategoriesAdapter(
    private val onClickListener: (Category) -> Unit,

): ListAdapter <Category, CategoriesAdapter.CategoriesViewHolder>(CategoriesDiffCallback()) {
    private lateinit var recyclerView: RecyclerView

    private var selectedPosition = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)
        holder.itemView.isSelected = selectedPosition == position
        holder.itemView.setOnClickListener {

            val previousPosition = selectedPosition
            selectedPosition = holder.adapterPosition

            notifyItemChanged(previousPosition)
            notifyItemChanged(selectedPosition)
            onClickListener(category)
        }
    }
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }


    inner class CategoriesViewHolder(private val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            Log.i("Category",category.toString())
            binding.categoryName.text = category.name
            binding.categoryButton.setImageResource(category.image)
        }
    }
   class CategoriesDiffCallback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }

}