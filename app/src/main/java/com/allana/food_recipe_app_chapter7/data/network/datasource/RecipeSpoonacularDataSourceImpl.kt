package com.allana.food_recipe_app_chapter7.data.network.datasource

import com.allana.food_recipe_app_chapter7.data.model.response.recipe.detail.RecipeDetailResponse
import com.allana.food_recipe_app_chapter7.data.network.services.RecipeApiService
import javax.inject.Inject

// TODO jadikan satu sama recipeDataSource
// TODO hapus class yg ngga terpakai
class RecipeSpoonacularDataSourceImpl
@Inject constructor(private val recipeApiService: RecipeApiService): RecipeSpoonacularDataSource{
    override suspend fun getRecipeDetail(recipeId: Int): RecipeDetailResponse {
        return recipeApiService.getRecipeDetail(recipeId)
    }

}