package com.allana.food_recipe_app_chapter7.ui.splash

import com.allana.food_recipe_app_chapter7.base.arch.BaseRepositoryImpl
import com.allana.food_recipe_app_chapter7.data.local.datasource.LocalAuthDataSource
import com.allana.food_recipe_app_chapter7.data.network.datasource.auth.AuthApiDataSource
import com.allana.food_recipe_app_chapter7.data.network.model.response.auth.BaseAuthResponse
import com.allana.food_recipe_app_chapter7.data.network.model.response.auth.User
import javax.inject.Inject

class SplashScreenRepository @Inject constructor(
    private val authApiDataSource: AuthApiDataSource,
    private val localDataSource: LocalAuthDataSource
) : BaseRepositoryImpl(), SplashScreenContract.Repository {

    override suspend fun getSyncUser(): BaseAuthResponse<User, String> {
        return authApiDataSource.getSyncUser()
    }

    override fun isUserLoggedIn(): Boolean {
        return localDataSource.isUserLoggedIn()
    }

    override fun clearSession() {
        localDataSource.clearSession()
    }
}