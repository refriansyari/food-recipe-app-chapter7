package com.allana.food_recipe_app_chapter7.data.network.model.response.auth

import com.google.gson.annotations.SerializedName

data class BaseAuthResponse<D,E>(
    @SerializedName("data")
    var data: D,
    @SerializedName("success")
    var issuccess: Boolean,
    @SerializedName("errors")
    var errors: E
)
