package com.allana.food_recipe_app_chapter7.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import com.allana.food_recipe_app_chapter7.base.arch.BaseActivity
import com.allana.food_recipe_app_chapter7.base.model.Resource
import com.allana.food_recipe_app_chapter7.data.local.preference.SessionPreference
import com.allana.food_recipe_app_chapter7.databinding.ActivitySplashScreenBinding
import com.allana.food_recipe_app_chapter7.ui.features.home.HomeFragment
import com.allana.food_recipe_app_chapter7.ui.intro.IntroScreenActivity
import com.allana.food_recipe_app_chapter7.ui.loginpage.LoginPageActivity
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreenActivity : BaseActivity<ActivitySplashScreenBinding, SplashScreenViewModel>(ActivitySplashScreenBinding::inflate),
    SplashScreenContract.View {
    private val timer: CountDownTimer by lazy {
        object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                if(SessionPreference(this@SplashScreenActivity, Gson()).isAppOpenedFirstTime){
                    val intent = Intent(this@SplashScreenActivity, IntroScreenActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else{
                    checkLoginStatus()
                }
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSplashTimer()
    }

    private fun setSplashTimer() {
        timer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }

    override fun observeData() {
        super.observeData()
        getViewModel().getSyncUserLiveData().observe(this) {
            when (it) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    navigateToHome()
                }
                is Resource.Error -> {
                    deleteSession().also {
                        navigateToLogin()
                    }
                }
            }
        }
    }

    override fun initView() {}

    override fun checkLoginStatus() {
        if(getViewModel().isUserLoggedIn()){
            getViewModel().getSyncUser()
        }else{
            navigateToLogin()
        }
    }

    override fun deleteSession() {
        getViewModel().clearSession()
    }

    override fun navigateToLogin() {
        val intent = Intent(this@SplashScreenActivity, LoginPageActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun navigateToHome() {
        val intent = Intent(this@SplashScreenActivity, HomeFragment::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}