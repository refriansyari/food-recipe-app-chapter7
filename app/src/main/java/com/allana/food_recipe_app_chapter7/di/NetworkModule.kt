package com.allana.food_recipe_app_chapter7.di

import android.content.Context
import com.allana.food_recipe_app_chapter7.data.network.services.RecipeApiService
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // TODO add auth api services

    @Singleton
    @Provides
    fun provideRecipeApiServices(chuckerInterceptor: ChuckerInterceptor): RecipeApiService {
        return RecipeApiService.invoke(chuckerInterceptor)
    }

    @Singleton
    @Provides
    fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context).build()
    }
}