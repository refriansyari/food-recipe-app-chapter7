package com.allana.food_recipe_app_chapter7.ui.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.allana.food_recipe_app_chapter7.base.arch.BaseFragment
import com.allana.food_recipe_app_chapter7.base.model.Resource
import com.allana.food_recipe_app_chapter7.data.model.response.recipe.Recipe
import com.allana.food_recipe_app_chapter7.databinding.FragmentHomeBinding
import com.allana.food_recipe_app_chapter7.ui.features.home.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate),
    HomeListContract.View {

    private lateinit var adapter: HomeAdapter

    override fun initView() {
        getData()
//        initSwipeRefresh()
    }

    override fun initList() {
        adapter = HomeAdapter {
            DetailActivity.startActivity(requireContext(), it.id)
        }
        getViewBinding().rvRecipe.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = this@HomeFragment.adapter
        }
    }

    override fun getData() {
        getViewModel().getAllRecipes()
    }

    override fun showLoading(isVisible: Boolean) {
        getViewBinding().progressBar.isVisible = isVisible
    }

    override fun showContent(isVisible: Boolean) {
        getViewBinding().rvRecipe.isVisible = isVisible
    }

    override fun showError(isErrorEnabled: Boolean, msg: String?) {
        getViewBinding().tvMessage.isVisible = isErrorEnabled
        getViewBinding().tvMessage.text = msg
    }

    override fun observeData() {
        getViewModel().getRecipeListLiveData().observe(this) {
            when (it) {
                is Resource.Loading -> {
                    showLoading(true)
                    showContent(false)
                    showError(false, null)
                }
                is Resource.Success -> {
                    showLoading(false)
                    showContent(true)
                    showError(false, null)

                    initList()
                    setAdapter(it.data)
                }
                is Resource.Error -> {
                    showLoading(false)
                    showContent(false)
                    showError(true, it.message)
                }
            }
        }
    }

    private fun setAdapter(data: List<Recipe>?) {
        data?.let { adapter.setItems(it) }
    }

//    override fun initSwipeRefresh() {
//        getViewBinding().swipeRefresh.setOnRefreshListener {
//            getViewBinding().swipeRefresh.isRefreshing = false
//            getData()
//        }
//    }
}