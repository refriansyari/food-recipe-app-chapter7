package com.allana.food_recipe_app_chapter7.di

import com.allana.food_recipe_app_chapter7.data.local.preference.datasource.LocalAuthDataSource
import com.allana.food_recipe_app_chapter7.data.network.datasource.RecipeDataSource
import com.allana.food_recipe_app_chapter7.data.network.datasource.auth.AuthApiDataSource
import com.allana.food_recipe_app_chapter7.ui.features.home.HomeRepository
import com.allana.food_recipe_app_chapter7.ui.loginpage.LoginPageRepository
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

    @Singleton
    @Provides

    fun provideLoginPageRepository(
        authApiDataSource: AuthApiDataSource,
        localAuthDataSource: LocalAuthDataSource
    ): LoginPageRepository {
        return LoginPageRepository(authApiDataSource, localAuthDataSource)
    }

    // TODO add detailRecipeRepo

    // TODO add detailSplashRepo

    // TODO add profileRepo
}