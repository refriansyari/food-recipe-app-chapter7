package com.allana.food_recipe_app_chapter7.data.network.datasource

import com.allana.food_recipe_app_chapter7.data.network.model.response.recipe.detail.RecipeDetailResponse

interface RecipeSpoonacularDataSource {
    suspend fun getRecipeDetail(recipeId: Int): RecipeDetailResponse
}