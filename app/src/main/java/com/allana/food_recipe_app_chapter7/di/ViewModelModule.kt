package com.allana.food_recipe_app_chapter7.di

import com.allana.food_recipe_app_chapter7.base.arch.GenericViewModelFactory
import com.allana.food_recipe_app_chapter7.ui.features.home.HomeRepository
import com.allana.food_recipe_app_chapter7.ui.features.home.HomeViewModel
import com.allana.food_recipe_app_chapter7.ui.features.home.detail.DetailRepository
import com.allana.food_recipe_app_chapter7.ui.features.home.detail.DetailViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object ViewModelModule {

    // TODO add detailViewModel
    @Provides
    @ActivityScoped
    fun provideCoinListViewModel(
        homeRepository: HomeRepository
    ): HomeViewModel {
        return GenericViewModelFactory(HomeViewModel(homeRepository)).create(
            HomeViewModel::class.java
        )
    }
    @Provides
    @ActivityScoped
    fun provideDetailViewModel(detailRepository: DetailRepository): DetailViewModel{
        return GenericViewModelFactory(DetailViewModel(detailRepository)).create(
            DetailViewModel::class.java
        )
    }
    // TODO add loginModel
    // TODO add registerViewModel
    // TODO add splashViewModel
    // TODO add profileViewModel
}