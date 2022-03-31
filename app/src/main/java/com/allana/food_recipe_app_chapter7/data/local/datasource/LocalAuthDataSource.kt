package com.allana.food_recipe_app_chapter7.data.local.datasource

import com.allana.food_recipe_app_chapter7.data.model.response.auth.User

interface LocalAuthDataSource {
    fun getAuthToken(): String?
    fun setAuthToken(authToken: String?)
    fun isUserLoggedIn(): Boolean
    fun saveUserData(user: User)
    fun getUserData(): User?
    fun clearSession()
}