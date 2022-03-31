package com.allana.food_recipe_app_chapter7.data.network.datasource.auth

import com.allana.food_recipe_app_chapter7.data.model.request.AuthRequest
import com.allana.food_recipe_app_chapter7.data.model.response.auth.BaseAuthResponse
import com.allana.food_recipe_app_chapter7.data.model.response.auth.User
import java.io.File

interface AuthApiDataSource {
    suspend fun getSyncUser(): BaseAuthResponse<User, String>
    suspend fun getProfileData(): BaseAuthResponse<User, String>
    suspend fun postRegisterUser(registerRequest: AuthRequest): BaseAuthResponse<User, String>
    suspend fun postLoginUser(loginrequest: AuthRequest): BaseAuthResponse<User, String>
    suspend fun updateProfileData(
        username: String,
        email: String,
        profilePhoto: File? = null
    ): BaseAuthResponse<User, String>
}