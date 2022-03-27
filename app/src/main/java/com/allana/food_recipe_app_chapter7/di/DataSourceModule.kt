package com.allana.food_recipe_app_chapter7.di

import com.allana.food_recipe_app_chapter7.data.network.datasource.RecipeDataSource
import com.allana.food_recipe_app_chapter7.data.network.datasource.RecipeDataSourceImpl
import com.allana.food_recipe_app_chapter7.data.network.services.RecipeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    // TODO add auth
    @Singleton
    @Provides
    fun provideRecipeDataSource(recipeApiService: RecipeApiService): RecipeDataSource {
        return RecipeDataSourceImpl(recipeApiService)
    }
    // TODO add localDataSource
}