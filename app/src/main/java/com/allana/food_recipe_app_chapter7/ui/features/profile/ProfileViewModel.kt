package com.allana.food_recipe_app_chapter7.ui.features.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.allana.food_recipe_app_chapter7.base.arch.BaseContract
import com.allana.food_recipe_app_chapter7.base.arch.BaseViewModelImpl
import com.allana.food_recipe_app_chapter7.base.model.Resource
import com.allana.food_recipe_app_chapter7.data.network.model.response.auth.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject constructor(private val repository: ProfileRepository) : ProfileContract.ViewModel,
    BaseViewModelImpl() {

    private val getProfileResponseLiveData = MutableLiveData<Resource<User?>>()

    override fun getProfileData() {
        getProfileResponseLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getProfileData()
                viewModelScope.launch(Dispatchers.Main) {
                    getProfileResponseLiveData.value = Resource.Success(response)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    getProfileResponseLiveData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }

    override fun getProfileLiveData(): MutableLiveData<Resource<User?>> = getProfileResponseLiveData

    override fun logout() {
        repository.clearSession()
    }
}