package com.allana.food_recipe_app_chapter7.data.model.response.recipe


import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("recipes")
    val recipes: List<Recipe>?
)