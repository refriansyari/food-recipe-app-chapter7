package com.allana.food_recipe_app_chapter7.ui.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allana.food_recipe_app_chapter7.base.arch.BaseFragment
import com.allana.food_recipe_app_chapter7.databinding.FragmentHomeBinding
import com.allana.food_recipe_app_chapter7.ui.features.home.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate),
    HomeListContract.View {

    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

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

    override fun initViewModel(): HomeViewModel {
        TODO("Not yet implemented")
    }

}