package com.allana.food_recipe_app_chapter7.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {
    // TODO add session preference
    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}