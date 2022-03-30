package com.allana.food_recipe_app_chapter7.di

import com.allana.food_recipe_app_chapter7.base.arch.GenericViewModelFactory
import com.allana.food_recipe_app_chapter7.ui.features.favorite.FavoriteRecipeRepository
import com.allana.food_recipe_app_chapter7.ui.features.favorite.FavoriteRecipeViewModel
import com.allana.food_recipe_app_chapter7.ui.features.home.HomeRepository
import com.allana.food_recipe_app_chapter7.ui.features.home.HomeViewModel
import com.allana.food_recipe_app_chapter7.ui.features.profile.ProfileRepository
import com.allana.food_recipe_app_chapter7.ui.features.profile.ProfileViewModel
import com.allana.food_recipe_app_chapter7.ui.features.home.detail.DetailRepository
import com.allana.food_recipe_app_chapter7.ui.features.home.detail.DetailViewModel
import com.allana.food_recipe_app_chapter7.ui.features.profile.editprofile.EditProfileRepository
import com.allana.food_recipe_app_chapter7.ui.features.profile.editprofile.EditProfileViewModel
import com.allana.food_recipe_app_chapter7.ui.loginpage.LoginPageRepository
import com.allana.food_recipe_app_chapter7.ui.loginpage.LoginPageViewModel
import com.allana.food_recipe_app_chapter7.ui.register.RegisterRepository
import com.allana.food_recipe_app_chapter7.ui.register.RegisterViewModel
import com.allana.food_recipe_app_chapter7.ui.splash.SplashScreenRepository
import com.allana.food_recipe_app_chapter7.ui.splash.SplashScreenViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object ViewModelModule {

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
    fun provideDetailViewModel(detailRepository: DetailRepository): DetailViewModel {
        return GenericViewModelFactory(DetailViewModel(detailRepository)).create(
            DetailViewModel::class.java
        )
    }

    @Provides
    @ActivityScoped
    fun provideLoginPageViewModel(
        repository : LoginPageRepository
    ) : LoginPageViewModel{
        return GenericViewModelFactory(LoginPageViewModel(repository)).create(
            LoginPageViewModel::class.java
        )
    }

    @ActivityScoped
    @Provides
    fun provideSplashScreenViewModel(splashScreenRepository: SplashScreenRepository): SplashScreenViewModel {
        return GenericViewModelFactory(SplashScreenViewModel(splashScreenRepository))
            .create(SplashScreenViewModel::class.java)
    }

    @ActivityScoped
    @Provides
    fun provideFavoriteRecipeViewModel(favoriteRecipeRepository: FavoriteRecipeRepository): FavoriteRecipeViewModel {
        return GenericViewModelFactory(FavoriteRecipeViewModel(favoriteRecipeRepository))
            .create(FavoriteRecipeViewModel::class.java)
    }

    @Provides
    @ActivityScoped
    fun provideProfileViewModel(
        repository : ProfileRepository
    ) : ProfileViewModel{
        return GenericViewModelFactory(ProfileViewModel(repository)).create(
            ProfileViewModel::class.java
        )
    }

    @Provides
    @ActivityScoped
    fun provideEditProfileViewModel(
        repository : EditProfileRepository
    ) : EditProfileViewModel {
        return GenericViewModelFactory(EditProfileViewModel(repository)).create(
            EditProfileViewModel::class.java
        )
    }

    @Provides
    @ActivityScoped
    fun provideRegisterViewModel(
        registerRepository: RegisterRepository
    ): RegisterViewModel {
        return GenericViewModelFactory(RegisterViewModel(registerRepository)).create(
            RegisterViewModel::class.java
        )
    }
}