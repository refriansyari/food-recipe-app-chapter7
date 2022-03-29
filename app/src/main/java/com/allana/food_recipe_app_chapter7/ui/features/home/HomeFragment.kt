package com.allana.food_recipe_app_chapter7.ui.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allana.food_recipe_app_chapter7.base.arch.BaseFragment
import com.allana.food_recipe_app_chapter7.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate),
    HomeListContract.View {

    private lateinit var adapter: HomeAdapter

    override fun getData() {
        getViewModel().getAllRecipes()
    }

    override fun initView() {
        initSwipeRefresh()
        initList()
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

    override fun initList() {
        adapter = HomeAdapter {
            DetailActivity.startActivity(context, it.id.hashCode())
        }
        getViewBinding().rvRecipe.apply {
            adapter = this@HomeFragment.adapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    override fun initSwipeRefresh() {
        getViewBinding().swipeRefresh.setOnRefreshListener {
            getViewBinding().swipeRefresh.isRefreshing = false
            getData()
        }
    }
}