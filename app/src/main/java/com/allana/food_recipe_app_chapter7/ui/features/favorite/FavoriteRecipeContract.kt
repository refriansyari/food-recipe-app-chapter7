package com.allana.food_recipe_app_chapter7.ui.features.favorite

import androidx.lifecycle.LiveData
import com.allana.food_recipe_app_chapter7.base.arch.BaseContract
import com.allana.food_recipe_app_chapter7.base.model.Resource
import com.allana.food_recipe_app_chapter7.data.local.room.entity.FavoriteRecipe

interface FavoriteRecipeContract {
    interface View : BaseContract.BaseView {
        fun initList()
        fun getListData()
        fun getSearchData(searchQuery: String)
    }

    interface ViewModel : BaseContract.BaseViewModel {
        fun getRecipeListLiveData(): LiveData<Resource<List<FavoriteRecipe>>>
        fun searchFavoriteRecipeLiveData(): LiveData<Resource<List<FavoriteRecipe>>>
        fun getAllRecipes()
        fun searchFavoriteRecipe(searchQuery: String)
    }

    interface Repository : BaseContract.BaseRepository {
        suspend fun getAllFavoriteRecipes(): List<FavoriteRecipe>
        suspend fun searchFavoriteRecipe(searchQuery: String): List<FavoriteRecipe>
    }
}