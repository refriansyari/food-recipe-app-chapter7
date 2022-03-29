package com.allana.food_recipe_app_chapter7.ui.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.allana.food_recipe_app_chapter7.base.arch.BaseViewModelImpl
import com.allana.food_recipe_app_chapter7.base.model.Resource
import com.allana.food_recipe_app_chapter7.data.model.response.auth.User
import com.allana.food_recipe_app_chapter7.data.model.response.recipe.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : BaseViewModelImpl(), HomeListContract.ViewModel {

    private val recipeLiveData = MutableLiveData<Resource<List<Recipe>>>()
    override fun getRecipeListLiveData(): LiveData<Resource<List<Recipe>>> {
        return recipeLiveData
    }

    override fun getAllRecipes() {
        recipeLiveData.value = Resource.Loading()

        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = repository.getAllRecipes()
                viewModelScope.launch (Dispatchers.Main){
                    recipeLiveData.value = response.recipes?.let { Resource.Success(it) }!!
                }
            } catch (e: Exception){
                viewModelScope.launch (Dispatchers.Main){
                    recipeLiveData.value = Resource.Error(e. localizedMessage.orEmpty())
                }
            }
        }
    }


    override fun deleteSession() {
        repository.deleteSession()
    }

    override fun getUser(): User? {
        return repository.getUser()
    }
}