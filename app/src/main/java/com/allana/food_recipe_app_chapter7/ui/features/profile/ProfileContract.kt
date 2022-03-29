package com.allana.food_recipe_app_chapter7.ui.features.profile

import androidx.lifecycle.MutableLiveData
import com.allana.food_recipe_app_chapter7.base.arch.BaseContract
import com.allana.food_recipe_app_chapter7.base.model.Resource
import com.allana.food_recipe_app_chapter7.data.network.model.response.auth.BaseAuthResponse
import com.allana.food_recipe_app_chapter7.data.network.model.response.auth.User

interface ProfileContract {
    interface View : BaseContract.BaseView {
        fun getData()
        fun setOnClickListener()
        fun setProfileData(data : User)
        fun logout()
        fun navigateToLoginActivity()
        fun showLogoutConfirmation()
    }

    interface ViewModel : BaseContract.BaseViewModel {
        fun getProfileData()
        fun getProfileLiveData() : MutableLiveData<Resource<User?>>
        fun logout()
    }

    interface Repository : BaseContract.BaseRepository {
        suspend fun getProfileData() : User?
        fun clearSession()
    }
}