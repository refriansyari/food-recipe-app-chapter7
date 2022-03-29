package com.allana.food_recipe_app_chapter7.ui.features.home

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

    override fun initList() {
        adapter = HomeAdapter {

        }
        getViewBinding().rvRecipe
    }

    override fun initSwipeRefresh() {

    }

}