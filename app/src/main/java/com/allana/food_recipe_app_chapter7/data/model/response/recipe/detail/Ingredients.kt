package com.allana.food_recipe_app_chapter7.data.model.response.recipe.detail

import com.google.gson.annotations.SerializedName

data class Ingredients(
    @SerializedName("original")
    var name: String? = null
)
