package com.allana.food_recipe_app_chapter7.data.network.services

import com.allana.food_recipe_app_chapter7.BuildConfig
import com.allana.food_recipe_app_chapter7.data.network.model.response.recipe.detail.RecipeDetailResponse
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface RecipeSpoonacularApiServices {

    @GET("recipes/{recipeId}/information?apiKey=6ea0d5be0db54fb59cf9ee4b285232f1&includeNutrition=false")
    suspend fun getRecipeDetail(@Path("recipeId") recipeId : Int): RecipeDetailResponse

    companion object {
        @JvmStatic
        operator fun invoke(chuckerInterceptor: ChuckerInterceptor): RecipeSpoonacularApiServices {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(chuckerInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()

            val retrofit =Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL_RECIPE_SPOONACULAR)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(RecipeSpoonacularApiServices::class.java)
        }
    }
}