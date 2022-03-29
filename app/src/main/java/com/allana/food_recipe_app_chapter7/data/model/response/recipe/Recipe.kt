package com.allana.food_recipe_app_chapter7.data.model.response.recipe


import com.google.gson.annotations.SerializedName

data class Recipe(

    @SerializedName("id")
    var id: Long? = null,
    @SerializedName("image")
    var image: String? = null,
    @SerializedName("title")
    var title: String? = null
)