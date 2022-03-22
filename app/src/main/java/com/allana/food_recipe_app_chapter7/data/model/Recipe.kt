package com.allana.food_recipe_app_chapter7.data.model


import com.google.gson.annotations.SerializedName

data class Recipe(

    @SerializedName("id")
    var id: Int?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("title")
    var title: String?
)