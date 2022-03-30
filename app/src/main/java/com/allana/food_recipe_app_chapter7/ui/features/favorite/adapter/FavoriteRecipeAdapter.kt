package com.allana.food_recipe_app_chapter7.ui.features.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.allana.food_recipe_app_chapter7.data.local.room.entity.FavoriteRecipe
import com.allana.food_recipe_app_chapter7.databinding.ItemListFavoriteRecipeBinding
import com.google.android.material.chip.Chip

class FavoriteRecipeAdapter(private val itemClick: (FavoriteRecipe) -> Unit) :
    RecyclerView.Adapter<FavoriteRecipeAdapter.FavoriteRecipeViewHolder>() {

    private var items: MutableList<FavoriteRecipe> = mutableListOf()

    fun setItems(items: List<FavoriteRecipe>) {
        clearItems()
        addItems(items)
        notifyDataSetChanged()
    }

    private fun addItems(items: List<FavoriteRecipe>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    private fun clearItems() {
        this.items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteRecipeViewHolder {
        val binding = ItemListFavoriteRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteRecipeViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: FavoriteRecipeViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size

    class FavoriteRecipeViewHolder(private val binding: ItemListFavoriteRecipeBinding, val itemClick: (FavoriteRecipe) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: FavoriteRecipe) {
            with(item) {
                itemView.setOnClickListener { itemClick(this) }
                binding.ivFavoriteRecipe.load(image)
                binding.tvTitleFavoriteRecipe.text = name
                generateChips(dishTypes)
            }
        }
        private fun generateChips(categories: List<String?>?) {
            categories?.filter { !it.isNullOrEmpty() }?.forEach {
                binding.tvDishTypeFavoriteRecipe.addView(
                    Chip(itemView.context).apply {
                        text = it
                        isClickable = false
                    })
            }
        }
    }
}