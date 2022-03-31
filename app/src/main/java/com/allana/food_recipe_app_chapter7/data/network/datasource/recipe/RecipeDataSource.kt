package com.allana.food_recipe_app_chapter7.data.network.datasource.recipe

import com.allana.food_recipe_app_chapter7.data.model.response.recipe.RecipeResponse
import com.allana.food_recipe_app_chapter7.data.model.response.recipe.detail.RecipeDetailResponse

interface RecipeDataSource {
    suspend fun getAllRecipes(): RecipeResponse
    suspend fun getRecipeDetail(recipeId: Long): RecipeDetailResponse
}