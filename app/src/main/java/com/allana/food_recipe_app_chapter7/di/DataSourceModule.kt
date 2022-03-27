package com.allana.food_recipe_app_chapter7.di

import com.allana.food_recipe_app_chapter7.data.local.datasource.LocalAuthDataSource
import com.allana.food_recipe_app_chapter7.data.local.datasource.LocalAuthDataSourceImpl
import com.allana.food_recipe_app_chapter7.data.local.preference.SessionPreference
import com.allana.food_recipe_app_chapter7.data.local.room.dao.FavoriteRecipeDao
import com.allana.food_recipe_app_chapter7.data.local.room.datasource.FavoriteRecipeDataSource
import com.allana.food_recipe_app_chapter7.data.local.room.datasource.FavoriteRecipeDataSourceImpl
import com.allana.food_recipe_app_chapter7.data.local.room.entity.FavoriteRecipe
import com.allana.food_recipe_app_chapter7.data.network.datasource.RecipeDataSource
import com.allana.food_recipe_app_chapter7.data.network.datasource.RecipeDataSourceImpl
import com.allana.food_recipe_app_chapter7.data.network.datasource.auth.AuthApiDataSource
import com.allana.food_recipe_app_chapter7.data.network.datasource.auth.AuthApiDataSourceImpl
import com.allana.food_recipe_app_chapter7.data.network.services.AuthApiServices
import com.allana.food_recipe_app_chapter7.data.network.services.RecipeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideAuthDataSource(authApiService: AuthApiServices): AuthApiDataSource {
        return AuthApiDataSourceImpl(authApiService)
    }
    @Singleton
    @Provides
    fun provideRecipeDataSource(recipeApiService: RecipeApiService): RecipeDataSource {
        return RecipeDataSourceImpl(recipeApiService)
    }

    @Singleton
    @Provides
    fun provideFavoriteRecipeDataSource(favoriteRecipeDao: FavoriteRecipeDao): FavoriteRecipeDataSource{
        return FavoriteRecipeDataSourceImpl(favoriteRecipeDao)
    }

    @Singleton
    @Provides
    fun provideLocalAuthDataSource(userPreference: SessionPreference): LocalAuthDataSource {
        return LocalAuthDataSourceImpl(userPreference)
    }
}