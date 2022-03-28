package com.allana.food_recipe_app_chapter7.ui.register

import com.allana.food_recipe_app_chapter7.base.arch.BaseRepositoryImpl
import com.allana.food_recipe_app_chapter7.data.model.request.AuthRequest
import com.allana.food_recipe_app_chapter7.data.model.response.auth.BaseAuthResponse
import com.allana.food_recipe_app_chapter7.data.model.response.auth.User
import com.allana.food_recipe_app_chapter7.data.network.datasource.auth.AuthApiDataSource
import javax.inject.Inject

class RegisterRepository
@Inject constructor(
    private val authApiDataSource: AuthApiDataSource
) : BaseRepositoryImpl(), RegisterContract.Repository {
    override suspend fun postRegisterUser(registerRequest: AuthRequest): BaseAuthResponse<User, String> {
        return authApiDataSource.postRegisterUser(registerRequest)
    }
}