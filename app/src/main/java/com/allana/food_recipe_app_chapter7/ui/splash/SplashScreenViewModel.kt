package com.allana.food_recipe_app_chapter7.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.allana.food_recipe_app_chapter7.base.arch.BaseViewModelImpl
import com.allana.food_recipe_app_chapter7.base.model.Resource
import com.allana.food_recipe_app_chapter7.data.model.response.auth.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(private val repository: SplashScreenRepository): BaseViewModelImpl(), SplashScreenContract.ViewModel {

    private val syncUserLiveData = MutableLiveData<Resource<User>>()

    override fun getSyncUserLiveData(): LiveData<Resource<User>> = syncUserLiveData

    override fun getSyncUser() {
        syncUserLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getSyncUser()
                viewModelScope.launch(Dispatchers.Main) {
                    syncUserLiveData.value = Resource.Success(response.data)
                }
            }catch (e : Exception){
                viewModelScope.launch(Dispatchers.Main) {
                    syncUserLiveData.value = Resource.Error(e.localizedMessage.orEmpty())
                }
            }
        }

    }

    override fun isUserLoggedIn(): Boolean {
        return repository.isUserLoggedIn()
    }

    override fun clearSession() {
        repository.clearSession()
    }
}