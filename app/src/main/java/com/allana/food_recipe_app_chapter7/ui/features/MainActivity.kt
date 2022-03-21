package com.allana.food_recipe_app_chapter7.ui.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.allana.food_recipe_app_chapter7.R
import com.allana.food_recipe_app_chapter7.databinding.ActivityMainBinding
import com.allana.food_recipe_app_chapter7.ui.features.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var homeFragment = HomeFragment()
    private var allRecipeFragment: Fragment = homeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        initView()
    }

    private fun initView() {
        binding.btmNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> {
                    setupFragment(homeFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun setupFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .hide(allRecipeFragment)
            .show(fragment)
            .commit()
        allRecipeFragment = fragment
    }
}