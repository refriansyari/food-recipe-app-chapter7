package com.allana.food_recipe_app_chapter7.data.network.model.response.recipe.detail

import com.google.gson.annotations.SerializedName

data class RecipeDetailResponse(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("image")
    var image: String? = null,
    @SerializedName("extendedIngredients")
    var ingredients: List<Ingredients>? = null,
    @SerializedName("instruction")
    var instruction: String? = null
)
