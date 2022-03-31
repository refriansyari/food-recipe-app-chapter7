package com.allana.food_recipe_app_chapter7.ui.features.home

import androidx.lifecycle.LiveData
import com.allana.food_recipe_app_chapter7.base.arch.BaseContract
import com.allana.food_recipe_app_chapter7.base.model.Resource
import com.allana.food_recipe_app_chapter7.data.model.response.auth.User
import com.allana.food_recipe_app_chapter7.data.model.response.recipe.Recipe
import com.allana.food_recipe_app_chapter7.data.model.response.recipe.RecipeResponse

interface HomeListContract {
    interface View : BaseContract.BaseView {
        fun initList()
        fun getData()
//        fun initSwipeRefresh()
    }

    interface ViewModel : BaseContract.BaseViewModel {
        fun getRecipeListLiveData(): LiveData<Resource<List<Recipe>>>
        fun getAllRecipes()
        fun deleteSession()
        fun getUser() : User?
    }

    interface Repository : BaseContract.BaseRepository {
        suspend fun getAllRecipes():RecipeResponse
        fun deleteSession()
        fun getUser() : User?
    }
}