package com.allana.food_recipe_app_chapter7.ui.features.favoriterecipe

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.allana.food_recipe_app_chapter7.base.arch.BaseFragment
import com.allana.food_recipe_app_chapter7.base.model.Resource
import com.allana.food_recipe_app_chapter7.data.local.room.entity.FavoriteRecipe
import com.allana.food_recipe_app_chapter7.databinding.FragmentFavoriteRecipeBinding
import com.allana.food_recipe_app_chapter7.ui.features.favorite.FavoriteRecipeContract
import com.allana.food_recipe_app_chapter7.ui.features.favorite.FavoriteRecipeViewModel
import com.allana.food_recipe_app_chapter7.ui.features.favorite.adapter.FavoriteRecipeAdapter
import com.allana.food_recipe_app_chapter7.ui.features.home.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteRecipeFragment : BaseFragment<FragmentFavoriteRecipeBinding, FavoriteRecipeViewModel>(FragmentFavoriteRecipeBinding::inflate),
FavoriteRecipeContract.View {

    private lateinit var adapter: FavoriteRecipeAdapter

    override fun initView() {
        getViewBinding().etSearchFavoriteRecipe.addTextChangedListener{ searchQuery ->
            searchQuery?.let {
                if (searchQuery.toString().isNotEmpty()) {
                    getSearchData(searchQuery.toString())
                }
            }
        }
        initList()
        getListData()
    }

    override fun initList() {
        adapter = FavoriteRecipeAdapter {
            DetailActivity.startActivity(context, it.idRecipe)
        }
        getViewBinding().rvFavoriteRecipe.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@FavoriteRecipeFragment.adapter
        }
    }

    override fun getListData() {
        getViewModel().getRecipeListLiveData()
    }

    override fun getSearchData(searchQuery: String) {
        val query = "%$searchQuery%"
        getViewModel().searchFavoriteRecipe(query)
    }

    override fun observeData() {
        super.observeData()
        getViewModel().getRecipeListLiveData().observe(this) {
            when(it) {
                is Resource.Loading -> {
                    showLoading(true)
                    showContent(false)
                    showError(false, null)
                }
                is Resource.Success -> {
                    showLoading(false)
                    showContent(true)
                    showError(false, null)
                    setDataAdapter(it.data)
                }
                is Resource.Error -> {
                    showLoading(false)
                    showContent(false)
                    showError(true, it.message)
                }
            }
        }
        getViewModel().searchFavoriteRecipeLiveData().observe(this) {
            when(it) {
                is Resource.Loading -> {
                    showLoading(true)
                    showContent(false)
                    showError(false, null)
                }
                is Resource.Success -> {
                    showLoading(false)
                    showContent(true)
                    showError(false, null)
                    setDataAdapter(it.data)
                }
                is Resource.Error -> {
                    showLoading(false)
                    showContent(false)
                    showError(true, it.message)
                }
            }
        }
    }

    private fun setDataAdapter(data: List<FavoriteRecipe>?) {
        data?.let {
            adapter.setItems(it)
        }
    }

    override fun showLoading(isVisible: Boolean) {
        getViewBinding().progressBar.isVisible = isVisible
    }

    override fun showContent(isVisible: Boolean) {
        getViewBinding().rvFavoriteRecipe.isVisible = isVisible
    }

    override fun showError(isErrorEnabled: Boolean, msg: String?) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}