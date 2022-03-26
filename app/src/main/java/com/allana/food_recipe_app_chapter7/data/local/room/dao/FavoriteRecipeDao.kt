package com.allana.food_recipe_app_chapter7.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.allana.food_recipe_app_chapter7.data.local.room.entity.FavoriteRecipe
@Dao
interface FavoriteRecipeDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteRecipe(favRecipe: FavoriteRecipe) : Long

    @Delete
    suspend fun deleteFavoriteRecipe(favRecipe: FavoriteRecipe) : Int

}