package com.allana.food_recipe_app_chapter7.data.network.datasource.recipe

import com.allana.food_recipe_app_chapter7.data.model.response.recipe.Recipe
import com.allana.food_recipe_app_chapter7.data.model.response.recipe.RecipeResponse
import com.allana.food_recipe_app_chapter7.data.model.response.recipe.detail.RecipeDetailResponse
import retrofit2.Call

interface RecipeDataSource {
    suspend fun getAllRecipes(): RecipeResponse
    suspend fun getRecipeDetail(recipeId: Int): RecipeDetailResponse
}