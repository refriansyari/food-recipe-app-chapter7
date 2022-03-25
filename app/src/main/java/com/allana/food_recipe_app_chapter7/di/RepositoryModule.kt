package com.allana.food_recipe_app_chapter7.di

import com.allana.food_recipe_app_chapter7.data.local.room.datasource.FavoriteRecipeDataSource
import com.allana.food_recipe_app_chapter7.data.network.datasource.RecipeDataSource
import com.allana.food_recipe_app_chapter7.ui.features.home.HomeRepository
import com.allana.food_recipe_app_chapter7.ui.features.home.detail.DetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRecipeListRepository(
        recipeDataSource: RecipeDataSource
    ): HomeRepository {
        // TODO add localDataSource
        return HomeRepository(recipeDataSource)
    }

    // TODO add detailRecipeRepo
    @Provides
    @Singleton
    fun provideDetailRepository(
        favoriteRecipeDataSource: FavoriteRecipeDataSource, recipeDataSource: RecipeDataSource
    ): DetailRepository{
        return DetailRepository(favoriteRecipeDataSource,recipeDataSource)
    }

    // TODO add detailSplashRepo

    // TODO add profileRepo
}