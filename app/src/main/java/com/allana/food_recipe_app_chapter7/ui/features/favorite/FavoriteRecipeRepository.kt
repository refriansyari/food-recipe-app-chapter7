package com.allana.food_recipe_app_chapter7.ui.features.favorite

import com.allana.food_recipe_app_chapter7.base.arch.BaseRepositoryImpl
import com.allana.food_recipe_app_chapter7.data.local.room.datasource.FavoriteRecipeDataSource
import com.allana.food_recipe_app_chapter7.data.local.room.entity.FavoriteRecipe
import javax.inject.Inject

class FavoriteRecipeRepository @Inject constructor(private val favoriteRecipeDataSource: FavoriteRecipeDataSource)
    : BaseRepositoryImpl(), FavoriteRecipeContract.Repository{

    override suspend fun getAllFavoriteRecipes(): List<FavoriteRecipe> {
        return favoriteRecipeDataSource.getAllFavoriteRecipe()
    }

    override suspend fun searchFavoriteRecipe(searchQuery: String): List<FavoriteRecipe> {
        return favoriteRecipeDataSource.searchFavoriteRecipe(searchQuery)
    }

}