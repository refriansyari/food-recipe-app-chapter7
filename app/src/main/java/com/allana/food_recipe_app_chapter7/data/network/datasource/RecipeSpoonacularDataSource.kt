package com.allana.food_recipe_app_chapter7.data.network.datasource

import com.allana.food_recipe_app_chapter7.data.model.response.recipe.detail.RecipeDetailResponse

// TODO jadikan satu sama recipeDataSource
// TODO hapus class yg ngga terpakai
interface RecipeSpoonacularDataSource {
    suspend fun getRecipeDetail(recipeId: Int): RecipeDetailResponse
}