package com.allana.food_recipe_app_chapter7.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.allana.food_recipe_app_chapter7.R
import com.allana.food_recipe_app_chapter7.data.local.preference.SessionPreference
import com.allana.food_recipe_app_chapter7.ui.features.MainActivity
import com.allana.food_recipe_app_chapter7.ui.intro.IntroScreenActivity
import com.allana.food_recipe_app_chapter7.ui.loginpage.LoginPageActivity
import com.google.gson.Gson

class SplashScreenActivity : AppCompatActivity() {
    private val timer: CountDownTimer by lazy {
        object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                if(SessionPreference(this@SplashScreenActivity, Gson()).isAppOpenedFirstTime){
                    val intent = Intent(this@SplashScreenActivity, IntroScreenActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else{
                    val intent = Intent(this@SplashScreenActivity, LoginPageActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        setSplashTimer()
    }

    private fun setSplashTimer() {
        timer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}