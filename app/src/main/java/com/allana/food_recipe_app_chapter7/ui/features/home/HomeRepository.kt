package com.allana.food_recipe_app_chapter7.ui.features.home

import com.allana.food_recipe_app_chapter7.base.arch.BaseRepositoryImpl
import com.allana.food_recipe_app_chapter7.data.local.datasource.LocalAuthDataSource
import com.allana.food_recipe_app_chapter7.data.model.response.auth.User
import com.allana.food_recipe_app_chapter7.data.model.response.recipe.RecipeResponse
import com.allana.food_recipe_app_chapter7.data.network.datasource.recipe.RecipeDataSource
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val datasource: RecipeDataSource,
    private val localAuthDataSource: LocalAuthDataSource
) :
    BaseRepositoryImpl(), HomeListContract.Repository {
    override suspend fun getAllRecipes(): RecipeResponse = datasource.getAllRecipes()

    override fun deleteSession() {
        TODO("Not yet implemented")
    }

    override fun getUser(): User? {
        TODO("Not yet implemented")
    }


}