package com.allana.food_recipe_app_chapter7.ui.loginpage

import com.allana.food_recipe_app_chapter7.base.arch.BaseRepositoryImpl
import com.allana.food_recipe_app_chapter7.data.local.preference.datasource.LocalDataAuthSource
import com.allana.food_recipe_app_chapter7.data.network.datasource.auth.AuthApiDataSource
import com.allana.food_recipe_app_chapter7.data.network.model.request.AuthRequest
import com.allana.food_recipe_app_chapter7.data.network.model.response.auth.BaseAuthResponse
import com.allana.food_recipe_app_chapter7.data.network.model.response.auth.User
import javax.inject.Inject

class LoginPageRepository @Inject constructor(
    private val authApiDataSource : AuthApiDataSource,
    private val localDataAuthSource: LocalDataAuthSource
) : BaseRepositoryImpl(), LoginPageContract.Repository{
    override suspend fun postLoginUser(loginRequest: AuthRequest): BaseAuthResponse<User, String> {
        val loginResponse = authApiDataSource.postLoginUser(loginRequest)
        if (loginResponse.issuccess){
            localDataAuthSource.setAuthToken(loginResponse.data.token)
            val userResponse = authApiDataSource.getProfileData()
            localDataAuthSource.saveUserData(userResponse.data)
        }
        return loginResponse
    }
}