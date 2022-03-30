package com.allana.food_recipe_app_chapter7.ui.features.profile

import android.content.Intent
import android.widget.Toast
import coil.load
import coil.transform.CircleCropTransformation
import com.allana.food_recipe_app_chapter7.R
import com.allana.food_recipe_app_chapter7.base.arch.BaseFragment
import com.allana.food_recipe_app_chapter7.base.model.Resource
import com.allana.food_recipe_app_chapter7.data.model.response.auth.User
import com.allana.food_recipe_app_chapter7.databinding.FragmentProfileBinding
import com.allana.food_recipe_app_chapter7.ui.features.profile.editprofile.EditProfileActivity
import com.allana.food_recipe_app_chapter7.ui.loginpage.LoginPageActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment: BaseFragment<FragmentProfileBinding, ProfileViewModel>(FragmentProfileBinding::inflate),
ProfileContract.View {
    override fun initView() {
        getData()
        setOnClickListener()
    }

    override fun getData() {
        getViewModel().getProfileData()
    }

    override fun setOnClickListener() {
        getViewBinding().llEditProfile.setOnClickListener {
            startActivity(Intent(requireContext(), EditProfileActivity::class.java))
        }
        getViewBinding().llLogout.setOnClickListener {
            showLogoutConfirmation()
        }
    }

    override fun onResume() {
        super.onResume()
        initView()
        observeData()
    }

    override fun setProfileData(data: User) {
        if (!data.photo.isNullOrEmpty()){
            getViewBinding().ivProfilePicture.load(data.photo) {
                crossfade(true)
                transformations(CircleCropTransformation())
                placeholder(R.drawable.ic_photo)
            }
        } else {
            getViewBinding().ivProfilePicture.load(R.drawable.ic_photo)
        }

        getViewBinding().tvName.text = data.username
        getViewBinding().tvUsername.text = getString(R.string.username_string, data.username)
    }

    override fun logout() {
        getViewModel().logout()
        Toast.makeText(requireContext(), getString(R.string.text_logout_successful), Toast.LENGTH_SHORT).show()
        navigateToLoginActivity()
    }

    override fun navigateToLoginActivity() {
        val intent = Intent(requireContext(), LoginPageActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun showLogoutConfirmation() {
        MaterialAlertDialogBuilder(requireContext())
            .apply {
                setTitle(getString(R.string.text_logout_profile))
                    .setMessage(getString(R.string.text_logout_confirmation))
                    .setPositiveButton(getString(R.string.text_logout)) { dialog, _ ->
                        logout()
                        dialog.dismiss()
                    }
                    .setNegativeButton(getString(R.string.text_cancel)) { dialog, _ ->
                        dialog.dismiss()
                    }
            }.create().show()
    }

    override fun observeData() {
        super.observeData()
        getViewModel().getProfileLiveData().observe(this) {
            when (it){
                is Resource.Loading -> {
                    showLoading(true)
                    showContent(false)
                    showError(false, null)
                }
                is Resource.Success -> {
                    showLoading(false)
                    showContent(true)
                    showError(false, null)
                    setProfileData(it.data!!)
                }
                is Resource.Error -> {
                    showLoading(false)
                    showContent(false)
                    showError(true, it.message)
                }
            }
        }
    }

}