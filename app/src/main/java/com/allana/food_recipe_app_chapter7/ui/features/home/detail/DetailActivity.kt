package com.allana.food_recipe_app_chapter7.ui.features.home.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import coil.load
import com.allana.food_recipe_app_chapter7.R
import com.allana.food_recipe_app_chapter7.base.arch.BaseActivity
import com.allana.food_recipe_app_chapter7.base.model.Resource
import com.allana.food_recipe_app_chapter7.data.local.room.entity.FavoriteRecipe
import com.allana.food_recipe_app_chapter7.data.model.response.recipe.detail.Ingredients
import com.allana.food_recipe_app_chapter7.data.model.response.recipe.detail.RecipeDetailResponse
import com.allana.food_recipe_app_chapter7.databinding.ActivityDetailBinding
import com.allana.food_recipe_app_chapter7.utils.Extension.textFromHtml
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity :
    BaseActivity<ActivityDetailBinding, DetailViewModel>(ActivityDetailBinding::inflate),
    DetailContract.View {

    private lateinit var recipeFavorite: FavoriteRecipe
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getIntentData()
    }

    override fun initView() {
        setClickListener()
    }

    override fun observeData() {
        getViewModel().getRecipeDetailResponse().observe(this) {
            when (it) {
                is Resource.Loading -> {
                    showLoading(true)
                    showContent(false)
//                    showError(false, null)
                }
                is Resource.Success -> {
                    showLoading(false)
                    showContent(true)
                    it.data?.let { data ->
                        setContentData(data)
                        addRecipeToFavorite(data)
                    }
//                    showError(false, null)
                }
                is Resource.Error -> {
                    showLoading(false)
                    showContent(false)
//                    showError(true, it.message)
                }
            }
        }
        getViewModel().getRecipeId().observe(this) {
            it?.let {
                getViewModel().getRecipeDetail(it)
            }

        }
    }

    override fun setContentData(data: RecipeDetailResponse) {
        getViewBinding().ivDetailRecipe.load(data.image)
        getViewBinding().tvTitleDetail.text = data.title
        generateChips(data.serving)
        showIngredients(data.ingredients)
        getViewBinding().tvInstructionDetail.textFromHtml(data.instruction)
    }

    private fun showIngredients(ingredients: ArrayList<Ingredients>?) {
        ingredients?.forEach {
            if (it != null) {
                getViewBinding().tvIngredientDetail.append(getString(R.string.text_placeholder_ingredients, it.name))
            }
        }
    }


    private fun generateChips(serving: List<String?>?) {
        serving?.filter { !it.isNullOrEmpty() }?.forEach {
            getViewBinding().cgCategory.addView(
                Chip(this).apply {
                    text = it
                    isClickable = false
                })
        }
    }

    override fun getIntentData() {
        getViewModel().setIntentData(intent.extras)
    }

    private fun setClickListener() {
        getViewBinding().flBtnBack.setOnClickListener {
            //back to home
            onBackPressed()
        }
    }

    private fun addRecipeToFavorite(detailRecipe: RecipeDetailResponse) {
        getViewBinding().ivBtnFavorite.setOnClickListener {
            getViewBinding().ivBtnFavorite.setImageResource(R.drawable.ic_baseline_favorite)
            isFavorite = !isFavorite
            if (isFavorite) {
                recipeFavorite =
                    FavoriteRecipe(
                        detailRecipe.id,
                        detailRecipe.title,
                        detailRecipe.image,
                        //detailRecipe.serving
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
        fun startActivity(context: Context?, recipeId: Long?) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRAS_RECIPE_DETAIL, recipeId)
            context?.startActivity(intent)
        }
    }
}