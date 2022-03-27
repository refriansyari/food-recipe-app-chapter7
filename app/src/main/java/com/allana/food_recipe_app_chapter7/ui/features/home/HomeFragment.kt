package com.allana.food_recipe_app_chapter7.ui.features.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.allana.food_recipe_app_chapter7.databinding.FragmentHomeBinding
import com.allana.food_recipe_app_chapter7.ui.features.home.search.SearchActivity
import dagger.hilt.android.AndroidEntryPoint

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
}