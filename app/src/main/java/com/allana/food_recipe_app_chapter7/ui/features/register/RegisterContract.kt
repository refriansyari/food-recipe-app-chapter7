package com.allana.food_recipe_app_chapter7.ui.features.register

import androidx.lifecycle.LiveData
import com.allana.food_recipe_app_chapter7.base.arch.BaseContract
import com.allana.food_recipe_app_chapter7.base.model.Resource
import com.allana.food_recipe_app_chapter7.data.network.model.request.AuthRequest
import com.allana.food_recipe_app_chapter7.data.network.model.response.auth.BaseAuthResponse
import com.allana.food_recipe_app_chapter7.data.network.model.response.auth.User

class RegisterContract {
    interface View : BaseContract.BaseView {
        fun initView()
        fun setToolbar()
        fun setOnClick()
        fun setLoadingState(isLoadingVisible: Boolean)
        fun checkFormValidation(): Boolean
        fun navigateToLogin()
    }

    interface ViewModel : BaseContract.BaseViewModel {
        fun getRegisterResponseLiveData(): LiveData<Resource<User>>
        fun registerUser(registerRequest: AuthRequest)
    }

    interface Repository : BaseContract.BaseRepository {
        suspend fun postRegisterUser(registerRequest: AuthRequest): BaseAuthResponse<User, String>
    }
}