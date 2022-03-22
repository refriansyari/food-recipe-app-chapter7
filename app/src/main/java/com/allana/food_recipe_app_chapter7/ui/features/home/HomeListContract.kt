package com.allana.food_recipe_app_chapter7.ui.features.home

import androidx.lifecycle.LiveData
import com.allana.food_recipe_app_chapter7.base.arch.BaseContract
import com.allana.food_recipe_app_chapter7.base.model.Resource
import com.allana.food_recipe_app_chapter7.data.model.Recipe

interface HomeListContract {
    interface View : BaseContract.BaseView {
        fun initList()
        fun getData()
        fun initSwipeRefresh()
    }

    interface ViewModel : BaseContract.BaseViewModel {
        fun getRecipeListLiveData(): LiveData<Resource<List<Recipe>>>
        fun getAllRecipes()
    }

    interface Repository : BaseContract.BaseRepository {
        suspend fun getAllRecipes(): List<Recipe>
    }
}