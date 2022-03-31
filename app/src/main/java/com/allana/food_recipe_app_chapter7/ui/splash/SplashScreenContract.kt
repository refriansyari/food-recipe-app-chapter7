package com.allana.food_recipe_app_chapter7.ui.splash

import androidx.lifecycle.LiveData
import com.allana.food_recipe_app_chapter7.base.arch.BaseContract
import com.allana.food_recipe_app_chapter7.base.model.Resource
import com.allana.food_recipe_app_chapter7.data.model.response.auth.BaseAuthResponse
import com.allana.food_recipe_app_chapter7.data.model.response.auth.User

interface SplashScreenContract {
    interface View : BaseContract.BaseView {
        fun checkLoginStatus()
        fun deleteSession()
        fun navigateToLogin()
        fun navigateToHome()
    }

    interface ViewModel : BaseContract.BaseViewModel{
        fun getSyncUserLiveData(): LiveData<Resource<User>>
        fun getSyncUser()
        fun isUserLoggedIn(): Boolean
        fun clearSession()
    }

    interface Repository : BaseContract.BaseRepository{
        suspend fun getSyncUser(): BaseAuthResponse<User, String>
        fun isUserLoggedIn(): Boolean
        fun clearSession()
    }
}