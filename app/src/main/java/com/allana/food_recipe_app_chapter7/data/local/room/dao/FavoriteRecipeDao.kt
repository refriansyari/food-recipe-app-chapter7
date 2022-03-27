package com.allana.food_recipe_app_chapter7.data.local.room.dao

import androidx.room.*
import com.allana.food_recipe_app_chapter7.data.local.room.entity.FavoriteRecipe
@Dao
interface FavoriteRecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteRecipe(favRecipe: FavoriteRecipe) : Long

    @Delete
    suspend fun deleteFavoriteRecipe(favRecipe: FavoriteRecipe) : Int

    @Query("SELECT * FROM favRecipe")
    suspend fun getAllFavoriteRecipe(): List<FavoriteRecipe>

    @Query("SELECT * FROM favRecipe WHERE name LIKE :searchQuery")
    suspend fun searchFavoriteRecipe(searchQuery: String): List<FavoriteRecipe>

}