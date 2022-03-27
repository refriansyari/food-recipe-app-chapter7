package com.allana.food_recipe_app_chapter7.ui.features.home.detail

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.allana.food_recipe_app_chapter7.base.arch.BaseViewModelImpl
import com.allana.food_recipe_app_chapter7.base.model.Resource
import com.allana.food_recipe_app_chapter7.data.local.room.entity.FavoriteRecipe
import com.allana.food_recipe_app_chapter7.data.model.response.recipe.detail.RecipeDetailResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
@Inject constructor(private val repository: DetailRepository):
    BaseViewModelImpl(), DetailContract.ViewModel {

    private val recipeId = MutableLiveData<Int?>()
    private val recipeDetailLiveData = MutableLiveData<Resource<RecipeDetailResponse>>()
    private val insertRecipeLiveData = MutableLiveData<Resource<Long>>()

    override fun getRecipeDetailResponse(): LiveData<Resource<RecipeDetailResponse>> = recipeDetailLiveData

    override fun getRecipeId(): LiveData<Int?> = recipeId

    override fun setIntentData(extras: Bundle?) {
        if (extras != null) {
            recipeId.value = extras.getString(DetailActivity.EXTRAS_RECIPE_DETAIL)?.toInt()
        }
    }

    override fun getRecipeDetail(id: Int) {
        recipeDetailLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getRecipeDetail(id)
                viewModelScope.launch(Dispatchers.Main){
                    recipeDetailLiveData.value = Resource.Success(response)
                }
            } catch (e: Exception){
                viewModelScope.launch(Dispatchers.Main) {
                    recipeDetailLiveData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }

    override fun insertRecipeFavorite(favRecipe: FavoriteRecipe) {
        insertRecipeLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.insertRecipeFavorite(favRecipe)
                viewModelScope.launch(Dispatchers.Main){
                    insertRecipeLiveData.value = Resource.Success(response)
                }
            } catch (e: Exception){
                viewModelScope.launch(Dispatchers.Main) {
                    insertRecipeLiveData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }
}