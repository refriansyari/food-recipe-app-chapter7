package com.allana.food_recipe_app_chapter7.ui.features.favoriterecipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.allana.food_recipe_app_chapter7.base.arch.BaseViewModelImpl
import com.allana.food_recipe_app_chapter7.base.model.Resource
import com.allana.food_recipe_app_chapter7.data.local.room.entity.FavoriteRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteRecipeViewModel @Inject constructor(private val repository: FavoriteRecipeRepository)
    : BaseViewModelImpl(), FavoriteRecipeContract.ViewModel{

    private val allRecipeLiveData = MutableLiveData<Resource<List<FavoriteRecipe>>>()
    private val searchFavoriteRecipeLiveData = MutableLiveData<Resource<List<FavoriteRecipe>>>()

    override fun getRecipeListLiveData(): LiveData<Resource<List<FavoriteRecipe>>> = allRecipeLiveData

    override fun searchFavoriteRecipeLiveData(): LiveData<Resource<List<FavoriteRecipe>>> = searchFavoriteRecipeLiveData

    override fun getAllRecipes() {
        allRecipeLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val favoriteRecipe = repository.getAllFavoriteRecipes()
                viewModelScope.launch(Dispatchers.Main) {
                    allRecipeLiveData.value = Resource.Success(favoriteRecipe)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    allRecipeLiveData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }

    override fun searchFavoriteRecipe(searchQuery: String) {
        searchFavoriteRecipeLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val searchRecipe = repository.searchFavoriteRecipe(searchQuery)
                viewModelScope.launch(Dispatchers.Main) {
                    searchFavoriteRecipeLiveData.value = Resource.Success(searchRecipe)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    allRecipeLiveData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }
}