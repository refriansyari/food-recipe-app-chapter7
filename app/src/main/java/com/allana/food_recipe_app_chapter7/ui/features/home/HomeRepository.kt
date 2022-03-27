package com.allana.food_recipe_app_chapter7.ui.features.home

import com.allana.food_recipe_app_chapter7.base.arch.BaseRepositoryImpl
import com.allana.food_recipe_app_chapter7.data.model.response.recipe.Recipe
import com.allana.food_recipe_app_chapter7.data.network.datasource.RecipeDataSource
import javax.inject.Inject

class HomeRepository @Inject constructor(private val datasource: RecipeDataSource) :
    BaseRepositoryImpl(), HomeListContract.Repository {

    override suspend fun getAllRecipes(): List<Recipe> {
        return datasource.getAllRecipes()
    }
}