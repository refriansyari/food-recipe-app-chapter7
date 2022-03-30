package com.allana.food_recipe_app_chapter7.ui.features.home.detail

import android.os.Bundle
import androidx.lifecycle.LiveData
import com.allana.food_recipe_app_chapter7.base.arch.BaseContract
import com.allana.food_recipe_app_chapter7.base.model.Resource
import com.allana.food_recipe_app_chapter7.data.local.room.entity.FavoriteRecipe
import com.allana.food_recipe_app_chapter7.data.model.response.recipe.detail.RecipeDetailResponse

interface DetailContract {

    interface View : BaseContract.BaseView{
        fun setContentData(data: RecipeDetailResponse)
        fun getIntentData()
    }

    interface ViewModel : BaseContract.BaseViewModel{
        fun insertRecipeFavorite(favRecipe: FavoriteRecipe)
        fun getRecipeDetailResponse(): LiveData<Resource<RecipeDetailResponse>>
        fun getRecipeId(): LiveData<Long?>
        fun setIntentData(extras : Bundle?)
        fun getRecipeDetail(id: Long)
    }

    interface Repository  : BaseContract.BaseRepository {
        suspend fun insertRecipeFavorite(favRecipe: FavoriteRecipe) : Long
        suspend fun getRecipeDetail(id: Long): RecipeDetailResponse
    }
}