package com.allana.food_recipe_app_chapter7.ui.features.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.allana.food_recipe_app_chapter7.data.model.response.recipe.Recipe
import com.allana.food_recipe_app_chapter7.databinding.ItemListRecipeBinding

class HomeAdapter(private val itemClick: (Recipe) -> Unit) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var items: MutableList<Recipe> = mutableListOf()

    fun setItems(items: List<Recipe>) {
        clearItems()
        addItems(items)
        notifyDataSetChanged()
    }

    fun addItems(items: List<Recipe>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clearItems() {
        this.items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemListRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size

    class HomeViewHolder(private val binding: ItemListRecipeBinding, val itemClick: (Recipe) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: Recipe) {
            with(item) {
                itemView.setOnClickListener { itemClick(this) }
                binding.ivFood.load(image)
                binding.tvRecipeName.text = title
            }
        }
    }
}