package com.allana.food_recipe_app_chapter7.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.allana.food_recipe_app_chapter7.data.local.room.entity.FavoriteRecipe

@Dao
interface FavoriteRecipeDao {
    @Insert
    suspend fun insertFavoriteRecipe(favRecipe: FavoriteRecipe) : Int

    @Delete
    suspend fun deleteFavoriteRecipe(favRecipe: FavoriteRecipe) : Int

}