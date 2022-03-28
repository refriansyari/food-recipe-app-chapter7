package com.allana.food_recipe_app_chapter7.di

import com.allana.food_recipe_app_chapter7.data.local.datasource.LocalAuthDataSource
import com.allana.food_recipe_app_chapter7.data.local.room.datasource.FavoriteRecipeDataSource
import com.allana.food_recipe_app_chapter7.data.network.datasource.RecipeDataSource
import com.allana.food_recipe_app_chapter7.data.network.datasource.auth.AuthApiDataSource
import com.allana.food_recipe_app_chapter7.ui.features.favoriterecipe.FavoriteRecipeRepository
import com.allana.food_recipe_app_chapter7.ui.features.home.HomeRepository
import com.allana.food_recipe_app_chapter7.ui.features.profile.ProfileRepository
import com.allana.food_recipe_app_chapter7.ui.loginpage.LoginPageRepository
import com.allana.food_recipe_app_chapter7.ui.splash.SplashScreenRepository
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

//    @Provides
//    @Singleton
//    fun provideDetailRepository(
//        favoriteRecipeDataSource: FavoriteRecipeDataSource, recipeDataSource: RecipeDataSource
//    ): DetailRepository{
//        return DetailRepository(favoriteRecipeDataSource,recipeDataSource)
//    }

    @Singleton
    @Provides
    fun provideSplashScreenRepository(
        authApiDataSource: AuthApiDataSource,
        localDataSource: LocalAuthDataSource
    ): SplashScreenRepository
    {
        return SplashScreenRepository(authApiDataSource, localDataSource)
    }

    @Singleton
    @Provides
    fun provideFavoriteRecipeRepository(
        favoriteRecipeDataSource: FavoriteRecipeDataSource
    ): FavoriteRecipeRepository
    {
        return FavoriteRecipeRepository(favoriteRecipeDataSource)
    }

    @Singleton
    @Provides

    fun provideProfileRepository(
        localAuthDataSource: LocalAuthDataSource
    ): ProfileRepository {
        return ProfileRepository(localAuthDataSource)
    }
}