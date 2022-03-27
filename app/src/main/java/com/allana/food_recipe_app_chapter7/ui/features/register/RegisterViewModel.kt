package com.allana.food_recipe_app_chapter7.ui.features.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.allana.food_recipe_app_chapter7.base.arch.BaseViewModelImpl
import com.allana.food_recipe_app_chapter7.base.model.Resource
import com.allana.food_recipe_app_chapter7.data.network.model.request.AuthRequest
import com.allana.food_recipe_app_chapter7.data.network.model.response.auth.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterViewModel @Inject constructor(private val repository: RegisterRepository) :
    BaseViewModelImpl(), RegisterContract.ViewModel {

    private val registerUserLiveData = MutableLiveData<Resource<User>>()

    override fun getRegisterResponseLiveData(): LiveData<Resource<User>> = registerUserLiveData

    override fun registerUser(registerRequest: AuthRequest) {
        registerUserLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.postRegisterUser(registerRequest)
                viewModelScope.launch(Dispatchers.Main) {
                    registerUserLiveData.value = Resource.Success(response.data)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    registerUserLiveData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }
}