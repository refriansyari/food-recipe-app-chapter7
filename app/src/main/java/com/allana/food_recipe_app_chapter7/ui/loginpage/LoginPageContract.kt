package com.allana.food_recipe_app_chapter7.ui.loginpage

import androidx.lifecycle.LiveData
import com.allana.food_recipe_app_chapter7.base.arch.BaseContract
import com.allana.food_recipe_app_chapter7.base.model.Resource
import com.allana.food_recipe_app_chapter7.data.network.model.request.AuthRequest
import com.allana.food_recipe_app_chapter7.data.network.model.response.auth.BaseAuthResponse
import com.allana.food_recipe_app_chapter7.data.network.model.response.auth.User

interface LoginPageContract {
    interface View: BaseContract.BaseView{
        fun setOnClick()
        fun isFormValid(): Boolean
        fun navigateToHome()
        fun navigateToRegister()
        fun setToolbar()
    }
    interface ViewModel: BaseContract.BaseViewModel{
        fun loginUser(loginRequest :AuthRequest)
        fun getLoginResultLiveData(): LiveData<Resource<User>>
    }
    interface Repository: BaseContract.BaseRepository{
        suspend fun postLoginUser(loginRequest:AuthRequest): BaseAuthResponse<User,String>

    }
}