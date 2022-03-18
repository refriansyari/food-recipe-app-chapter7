package com.allana.food_recipe_app_chapter7.data.arch

import android.util.Log
import androidx.lifecycle.ViewModel

open class BaseViewModelImpl : ViewModel(), BaseContract.BaseViewModel {
    override fun logResponse(msg: String?) {
        Log.d(BaseViewModelImpl::class.java.simpleName, msg.orEmpty())
    }
}