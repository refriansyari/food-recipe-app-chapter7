package com.allana.food_recipe_app_chapter7.ui.loginpage

import android.content.Intent
import android.widget.Toast
import androidx.core.view.isVisible
import com.allana.food_recipe_app_chapter7.R
import com.allana.food_recipe_app_chapter7.base.arch.BaseActivity
import com.allana.food_recipe_app_chapter7.base.model.Resource
import com.allana.food_recipe_app_chapter7.data.network.model.request.AuthRequest
import com.allana.food_recipe_app_chapter7.databinding.ActivityLoginPageBinding
import com.allana.food_recipe_app_chapter7.ui.features.MainActivity
import com.allana.food_recipe_app_chapter7.ui.features.home.HomeFragment
import com.allana.food_recipe_app_chapter7.utils.StringUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginPageActivity :
    BaseActivity<ActivityLoginPageBinding, LoginPageViewModel>(ActivityLoginPageBinding::inflate),
    LoginPageContract.View {
    override fun initView() {
        setToolbar()
        setOnClick()
    }

    override fun setOnClick() {
        getViewBinding().btnLogin.setOnClickListener {
            if (checkFormValidation()){
                getViewModel().loginUser(
                    AuthRequest(
                        email = getViewBinding().etEmailUsername.text.toString(),
                        password = getViewBinding().etPassword.text.toString()
                    )
                )
            }
        }
        getViewBinding().tvNavigateToRegister.setOnClickListener {
            navigateToRegister()
        }
    }

    override fun checkFormValidation(): Boolean {
        var isFormValid = true
        val email = getViewBinding().etEmailUsername.text.toString()
        val password = getViewBinding().etPassword.text.toString()
        when {
            email.isEmpty() -> {
                isFormValid = false
                getViewBinding().etEmailUsername.error = getString(R.string.error_email_username)
            }
            StringUtils.isEmailValid(email).not() -> {
                isFormValid = false
                getViewBinding().etEmailUsername.error = getString(R.string.invalid_email)
            }
        }
        if (password.isEmpty()) {
            isFormValid = false
            getViewBinding().etPassword.error = getString(R.string.error_password)
        }
        return isFormValid
    }

    override fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    override fun navigateToRegister() {
        //  TODO navigate to Register Activity
    }

    override fun observeData() {
        super.observeData()
        getViewModel().getLoginResultLiveData().observe(this) {
            when (it) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    Toast.makeText(this, R.string.text_succes_login, Toast.LENGTH_SHORT).show()
                    navigateToHome()
                }
                is Resource.Error -> {
                    showLoading(false)
                    Toast.makeText(this, R.string.text_error_login, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun showLoading(isVisible: Boolean) {
        super.showLoading(isVisible)
        getViewBinding().pbLogin.isVisible = isVisible
    }

    override fun setToolbar() {
        supportActionBar?.hide()
    }
}