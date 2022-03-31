package com.allana.food_recipe_app_chapter7.ui.intro

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.allana.food_recipe_app_chapter7.R
import com.allana.food_recipe_app_chapter7.data.local.preference.SessionPreference
import com.allana.food_recipe_app_chapter7.ui.loginpage.LoginPageActivity
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroCustomLayoutFragment
import com.google.gson.Gson

class IntroScreenActivity : AppIntro2() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        isSkipButtonEnabled = false

        setIndicatorColor(
            selectedIndicatorColor = ContextCompat.getColor(this, R.color.primary_color),
            unselectedIndicatorColor = ContextCompat.getColor(this, R.color.unselected_indicator)
        )

        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.layout_intro_first))
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.layout_intro_second))
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.layout_intro_third))
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        val intent = Intent(this, LoginPageActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        isFirstAppOpen()
    }

    private fun isFirstAppOpen() {
        SessionPreference(this, gson = Gson()).isAppOpenedFirstTime = false
    }
}