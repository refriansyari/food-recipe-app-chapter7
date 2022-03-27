package com.allana.food_recipe_app_chapter7.ui.features.home.detail

import android.content.Context
import android.content.Intent
import android.widget.Toast
import coil.load
import com.allana.food_recipe_app_chapter7.R
import com.allana.food_recipe_app_chapter7.base.arch.BaseActivity
import com.allana.food_recipe_app_chapter7.base.arch.GenericViewModelFactory
import com.allana.food_recipe_app_chapter7.base.model.Resource
import com.allana.food_recipe_app_chapter7.data.local.room.FavRecipeDatabase
import com.allana.food_recipe_app_chapter7.data.local.room.datasource.FavoriteRecipeDataSourceImpl
import com.allana.food_recipe_app_chapter7.data.local.room.entity.FavoriteRecipe
import com.allana.food_recipe_app_chapter7.data.model.response.recipe.detail.RecipeDetailResponse
import com.allana.food_recipe_app_chapter7.data.network.datasource.RecipeDataSourceImpl
import com.allana.food_recipe_app_chapter7.data.network.services.RecipeApiService
import com.allana.food_recipe_app_chapter7.databinding.ActivityDetailBinding
import com.allana.food_recipe_app_chapter7.utils.MapperExtension.toRecipeFavorite
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity :
    BaseActivity<ActivityDetailBinding, DetailViewModel>(ActivityDetailBinding::inflate),
    DetailContract.View {

    private lateinit var recipeFavorite: FavoriteRecipe
    private lateinit var recipeToFav: RecipeDetailResponse
    private var isFavorite: Boolean = false

    override fun initView() {
        getIntentData()
        addRecipeToFavorite()
    }

    override fun observeData() {
        getViewModel().getRecipeDetailResponse().observe(this) {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { data ->
                        setContentData(data)
                    }
                    Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                }
            }
            this.finish()
        }
        getViewModel().getRecipeId().observe(this) {
            it?.let {
                getViewModel().getRecipeDetail(it)
            }

        }
    }

    override fun setContentData(data: RecipeDetailResponse) {
        getViewBinding().ivDetailRecipe.load(data.image)
        getViewBinding().tvServingDetail.text = data.serving.toString()
        getViewBinding().tvIngredientDetail.text = data.ingredients.toString()
        getViewBinding().tvInstructionDetail.text = data.instruction
    }

    override fun getIntentData() {
        getViewModel().setIntentData(intent.extras)
    }

    private fun addRecipeToFavorite() {
        getViewBinding().flBtnFavorite.setOnClickListener {
            isFavorite = !isFavorite
            recipeFavorite = recipeToFav.let {
                FavoriteRecipe(
                    recipeToFav.id,
                    recipeToFav.title,
                    recipeToFav.image,
                    recipeToFav.serving.toString()
                )
            }
            if (isFavorite) {
                recipeFavorite.let {
                    viewModelInstance.insertRecipeFavorite(it).apply {
                        FavoriteRecipeDataSourceImpl(
                            FavRecipeDatabase.getInstance(this@DetailActivity).favoriteRecipeDao()
                        )
                    }
                }
            } else {
                // for delete favorite
            }
            setRecipeFavorite(isFavorite)
        }
    }

    private fun setRecipeFavorite(favorite: Boolean) {
        if (favorite) {
            getViewBinding().ivBtnFavorite.setImageResource(R.drawable.ic_baseline_favorite)
        } else {
            getViewBinding().ivBtnFavorite.setImageResource(R.drawable.ic_baseline_favorite_border)
        }
    }


    companion object {
        const val EXTRAS_RECIPE_DETAIL = "EXTRAS_RECIPE_DETAIL"

        @JvmStatic
        fun startActivity(context: Context?, recipeId: Int) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRAS_RECIPE_DETAIL, recipeId)
            context?.startActivity(intent)
        }
    }
}