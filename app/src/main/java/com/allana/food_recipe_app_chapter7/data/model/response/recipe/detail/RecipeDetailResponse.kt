package com.allana.food_recipe_app_chapter7.data.model.response.recipe.detail

import com.google.gson.annotations.SerializedName

data class RecipeDetailResponse(
    @SerializedName("id")
    var id: Long? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("image")
    var image: String? = null,
    @SerializedName("dishTypes")
    var serving: List<String>? = null,
    @SerializedName("extendedIngredients")
    var ingredients: ArrayList<Ingredients>? = null,
    @SerializedName("instructions")
    var instruction: String? = null
)
