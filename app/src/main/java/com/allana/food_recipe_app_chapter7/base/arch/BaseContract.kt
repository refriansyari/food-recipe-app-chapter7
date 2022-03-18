package com.allana.food_recipe_app_chapter7.base.arch

interface BaseContract {
    interface BaseView {
        fun observeData()
        fun showContent(isVisible: Boolean)
        fun showLoading(isVisible: Boolean)
        fun showError(isErrorEnabled: Boolean, msg: String? = null)
    }

    interface BaseViewModel {
        fun logResponse(msg : String?)
    }

    interface BaseRepository {
        fun logResponse(msg : String?)
    }
}