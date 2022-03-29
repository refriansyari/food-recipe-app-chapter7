package com.allana.food_recipe_app_chapter7.ui.features.profile

import com.allana.food_recipe_app_chapter7.base.arch.BaseRepositoryImpl
import com.allana.food_recipe_app_chapter7.data.local.datasource.LocalAuthDataSource
import com.allana.food_recipe_app_chapter7.data.network.model.response.auth.User
import javax.inject.Inject

class ProfileRepository
@Inject constructor(private val localDataSource: LocalAuthDataSource) : BaseRepositoryImpl(),
    ProfileContract.Repository {
    override suspend fun getProfileData(): User? {
        return localDataSource.getUserData()
    }

    override fun clearSession() {
        localDataSource.clearSession()
    }

}