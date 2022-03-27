package com.allana.food_recipe_app_chapter7.data.local.room.datasource

import com.allana.food_recipe_app_chapter7.data.local.room.entity.FavoriteRecipe

interface FavoriteRecipeDataSource {

    suspend fun insertFavoriteRecipe(favRecipe: FavoriteRecipe): Long

    suspend fun deleteFavoriteRecipe(favRecipe: FavoriteRecipe): Int

    suspend fun getAllFavoriteRecipe(): List<FavoriteRecipe>

    suspend fun searchFavoriteRecipe(searchQuery: String): List<FavoriteRecipe>

}