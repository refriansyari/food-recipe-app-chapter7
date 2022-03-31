package com.allana.food_recipe_app_chapter7.ui.features.home.detail

import com.allana.food_recipe_app_chapter7.base.arch.BaseRepositoryImpl
import com.allana.food_recipe_app_chapter7.data.local.room.datasource.FavoriteRecipeDataSource
import com.allana.food_recipe_app_chapter7.data.local.room.datasource.FavoriteRecipeDataSourceImpl
import com.allana.food_recipe_app_chapter7.data.local.room.entity.FavoriteRecipe
import com.allana.food_recipe_app_chapter7.data.model.response.recipe.detail.RecipeDetailResponse
import com.allana.food_recipe_app_chapter7.data.network.datasource.recipe.RecipeDataSource
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val recipeFavoriteDataSource: FavoriteRecipeDataSource,
    private val recipeDataSource: RecipeDataSource
    ): BaseRepositoryImpl(), DetailContract.Repository {
    override suspend fun insertRecipeFavorite(favRecipe: FavoriteRecipe): Long {
        return recipeFavoriteDataSource.insertFavoriteRecipe(favRecipe)
    }

    override suspend fun getRecipeDetail(id: Long): RecipeDetailResponse {
        return recipeDataSource.getRecipeDetail(id)
    }
}