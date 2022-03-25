package com.allana.food_recipe_app_chapter7.data.local.preference.datasource

import com.allana.food_recipe_app_chapter7.data.local.preference.SessionPreference
import com.allana.food_recipe_app_chapter7.data.network.model.response.auth.User
import javax.inject.Inject

class LocalAuthDataSourceImpl
@Inject constructor(private val userPreference: SessionPreference) : LocalAuthDataSource {
    override fun getAuthToken(): String? {
        return userPreference.authToken
    }

    override fun setAuthToken(authToken: String?) {
        userPreference.authToken = authToken
    }

    override fun isUserLoggedIn(): Boolean {
        return !userPreference.authToken.isNullOrEmpty()
    }

    override fun saveUserData(user: User) {
        userPreference.user = user
    }

    override fun getUserData(): User? {
        return userPreference.user
    }

    override fun clearSession() {
        userPreference.deleteSession()
    }
}