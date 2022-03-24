package com.allana.food_recipe_app_chapter7.ui.loginpage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.allana.food_recipe_app_chapter7.base.arch.BaseViewModelImpl
import com.allana.food_recipe_app_chapter7.base.model.Resource
import com.allana.food_recipe_app_chapter7.data.network.model.request.AuthRequest
import com.allana.food_recipe_app_chapter7.data.network.model.response.auth.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginPageViewModel @Inject constructor(
    private val repository: LoginPageRepository
) : BaseViewModelImpl(), LoginPageContract.ViewModel {

    private val loginResultLiveData = MutableLiveData<Resource<User>>()
    override fun loginUser(loginRequest: AuthRequest) {
        loginResultLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.postLoginUser(loginRequest)
                viewModelScope.launch (Dispatchers.Main){
                    loginResultLiveData.value = Resource.Success(response.data)
                }
            } catch (e:Exception){
                viewModelScope.launch (Dispatchers.Main){
                    loginResultLiveData.value = Resource.Error(e. localizedMessage.orEmpty())
                }
            }

        }
    }

    override fun getLoginResultLiveData(): LiveData<Resource<User>> = loginResultLiveData
}