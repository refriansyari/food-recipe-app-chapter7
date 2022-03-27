package com.allana.food_recipe_app_chapter7.ui.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.allana.food_recipe_app_chapter7.R
import com.allana.food_recipe_app_chapter7.databinding.ActivityMainBinding
import com.allana.food_recipe_app_chapter7.ui.features.favorite.FavoriteFragment
import com.allana.food_recipe_app_chapter7.ui.features.home.HomeFragment
import com.allana.food_recipe_app_chapter7.ui.features.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btmNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val homeFragment = HomeFragment()
        setupFragment(homeFragment)
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> {
                    val fragment = HomeFragment()
                    setupFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.action_favorite -> {
                    val fragment = FavoriteFragment()
                    setupFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.action_profile -> {
                    val fragment = ProfileFragment()
                    setupFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    private fun setupFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment, fragment.javaClass.simpleName)
            .commit()
    }
}