package com.allana.food_recipe_app_chapter7.data.network.datasource

import com.allana.food_recipe_app_chapter7.data.network.model.response.recipe.detail.RecipeDetailResponse
import com.allana.food_recipe_app_chapter7.data.network.services.RecipeSpoonacularApiServices
import javax.inject.Inject

class RecipeSpoonacularDataSourceImpl
@Inject constructor(private val recipeSpoonacularApiServices: RecipeSpoonacularApiServices): RecipeSpoonacularDataSource{
    override suspend fun getRecipeDetail(recipeId: Int): RecipeDetailResponse {
        return recipeSpoonacularApiServices.getRecipeDetail(recipeId)
    }

}