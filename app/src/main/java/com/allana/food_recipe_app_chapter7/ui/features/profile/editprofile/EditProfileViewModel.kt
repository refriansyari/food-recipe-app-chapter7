package com.allana.food_recipe_app_chapter7.ui.features.profile.editprofile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.allana.food_recipe_app_chapter7.base.arch.BaseViewModelImpl
import com.allana.food_recipe_app_chapter7.base.model.Resource
import com.allana.food_recipe_app_chapter7.data.model.response.auth.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(val repository: EditProfileRepository) :
    BaseViewModelImpl(), EditProfileContract.ViewModel {

    private val EditprofileLiveData = MutableLiveData<Resource<User>>()
    private val changeProfileResultLiveData = MutableLiveData<Resource<User>>()


    override fun getChangeProfileResultLiveData(): MutableLiveData<Resource<User>> =
        changeProfileResultLiveData

    override fun getProfileLiveData(): MutableLiveData<Resource<User>> = EditprofileLiveData

    override fun getProfileData() {
        EditprofileLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getProfileData()
                viewModelScope.launch(Dispatchers.Main) {
                    EditprofileLiveData.value = Resource.Success(response.data)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    EditprofileLiveData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }

    override fun updateProfileData(email: String, username: String, photoProfile: File?) {
        changeProfileResultLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.updateProfileData(email, username, photoProfile)
                viewModelScope.launch(Dispatchers.Main) {
                    changeProfileResultLiveData.value = Resource.Success(response.data)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    changeProfileResultLiveData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }
}