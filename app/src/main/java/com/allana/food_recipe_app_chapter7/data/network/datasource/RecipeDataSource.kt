package com.allana.food_recipe_app_chapter7.data.network.datasource

import com.allana.food_recipe_app_chapter7.data.model.Recipe
import com.allana.food_recipe_app_chapter7.data.network.model.response.recipe.detail.RecipeDetailResponse

interface RecipeDataSource {
    suspend fun getAllRecipes(): List<Recipe>
    suspend fun getRecipeDetail(recipeId: Int): RecipeDetailResponse
}