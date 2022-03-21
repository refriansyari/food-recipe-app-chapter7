package com.allana.food_recipe_app_chapter7.data.network.datasource

import com.allana.food_recipe_app_chapter7.data.model.Recipe
import com.allana.food_recipe_app_chapter7.data.network.services.RecipeApiService
import javax.inject.Inject

class RecipeDataSourceImpl @Inject constructor(private val recipeApiService: RecipeApiService) :
    RecipeDataSource {

    override suspend fun getAllRecipes(): List<Recipe> {
        return recipeApiService.getAllRecipes()
    }

}