package com.allana.food_recipe_app_chapter7.ui.features.home.detail

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.view.isVisible
import coil.load
import com.allana.food_recipe_app_chapter7.R
import com.allana.food_recipe_app_chapter7.base.arch.BaseActivity
import com.allana.food_recipe_app_chapter7.base.model.Resource
import com.allana.food_recipe_app_chapter7.data.local.room.entity.FavoriteRecipe
import com.allana.food_recipe_app_chapter7.data.model.response.recipe.detail.RecipeDetailResponse
import com.allana.food_recipe_app_chapter7.databinding.ActivityDetailBinding
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
        setClickListener()
    }

    override fun observeData() {
        getViewModel().getRecipeDetailResponse().observe(this) {
            when (it) {
                is Resource.Loading -> {
                    showLoading(true)
                    showContent(false)
                    showError(false, null)
                }
                is Resource.Success -> {
                    showLoading(false)
                    showContent(true)
                    it.data?.let { data ->
                        setContentData(data)
                    }
                    showError(false, null)
                }
                is Resource.Error -> {
                    showLoading(false)
                    showContent(false)
                    showError(true, it.message)
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

    private fun setClickListener() {
        //for add recipe to fav
        getViewBinding().ivBtnFavorite.setOnClickListener {
            getViewBinding().ivBtnFavorite.setImageResource(R.drawable.ic_baseline_favorite)
            addRecipeToFavorite()
        }
        getViewBinding().flBtnBack.setOnClickListener{
            //back to home
            onBackPressed()
        }
    }

    private fun addRecipeToFavorite() {
        isFavorite = !isFavorite
        if (isFavorite) {
            recipeFavorite = recipeToFav.let {
                FavoriteRecipe(
                    recipeToFav.id,
                    recipeToFav.title,
                    recipeToFav.image,
                    recipeToFav.serving.toString()
                )
            }
            recipeFavorite.let {
                viewModelInstance.insertRecipeFavorite(it)
            }
        }
    }

    override fun showLoading(isVisible: Boolean) {
        getViewBinding().progressBar.isVisible = isVisible
    }

    override fun showContent(isVisible: Boolean) {
        getViewBinding().svDetailRecipe.isVisible = isVisible
    }

    override fun showError(isErrorEnabled: Boolean, msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
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