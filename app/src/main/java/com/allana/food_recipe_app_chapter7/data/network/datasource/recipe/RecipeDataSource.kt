package com.allana.food_recipe_app_chapter7.data.network.datasource.recipe

import com.allana.food_recipe_app_chapter7.data.model.response.recipe.Recipe
import com.allana.food_recipe_app_chapter7.data.model.response.recipe.detail.RecipeDetailResponse

interface RecipeDataSource {
    suspend fun getAllRecipes(apiKey: String, number: Int): List<Recipe>
    suspend fun getRecipeDetail(recipeId: Int): RecipeDetailResponse
}