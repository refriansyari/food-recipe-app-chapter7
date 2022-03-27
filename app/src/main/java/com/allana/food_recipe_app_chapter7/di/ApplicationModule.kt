package com.allana.food_recipe_app_chapter7.di

import android.content.Context
import com.allana.food_recipe_app_chapter7.data.local.preference.SessionPreference
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {
    @Singleton
    @Provides
    fun provideSessionPreference(
        @ApplicationContext context:Context,
        gson: Gson
    ) : SessionPreference{
        return SessionPreference(context,gson)
    }
    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}