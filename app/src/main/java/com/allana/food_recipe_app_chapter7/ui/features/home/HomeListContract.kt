package com.allana.food_recipe_app_chapter7.ui.features.home

import androidx.lifecycle.LiveData
import com.allana.food_recipe_app_chapter7.base.arch.BaseContract
import com.allana.food_recipe_app_chapter7.base.model.Resource
import com.allana.food_recipe_app_chapter7.data.model.response.auth.User
import com.allana.food_recipe_app_chapter7.data.model.response.recipe.Recipe

interface HomeListContract {
    interface View : BaseContract.BaseView {
        fun initList()
        fun getData(apikey: String, number: Int)
        fun initSwipeRefresh()
    }

    interface ViewModel : BaseContract.BaseViewModel {
        fun getRecipeListLiveData(): LiveData<Resource<List<Recipe>>>
        fun getAllRecipes(apikey: String, number: Int)
        fun deleteSession()
        fun getUser() : User?
    }

    interface Repository : BaseContract.BaseRepository {
        suspend fun getAllRecipes(apikey: String, number: Int): List<Recipe>
        fun deleteSession()
        fun getUser() : User?
    }
}