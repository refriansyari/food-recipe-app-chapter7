package com.allana.food_recipe_app_chapter7.data.network.services

import com.allana.food_recipe_app_chapter7.BuildConfig
import com.allana.food_recipe_app_chapter7.data.model.response.recipe.RecipeResponse
import com.allana.food_recipe_app_chapter7.data.model.response.recipe.detail.RecipeDetailResponse
import com.allana.food_recipe_app_chapter7.data.network.services.ApiKey.API_KEY
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface RecipeApiService {

    /**
     * list recipe
     * **/
    @GET("random/")
    suspend fun getAllRecipes(
        @Query("apiKey") key: String = BuildConfig.API_KEY,
        @Query("number") number: Int = 20
    ): RecipeResponse

    /**
     * detail
     * **/
    // TODO sesuaikan dengan strukturnya
    @GET("{recipeId}/information")
    suspend fun getRecipeDetail(
        @Path("recipeId") recipeId: Long,
        @Query("apiKey") key: String = BuildConfig.API_KEY,
        @Query("number") includeNutrition: Boolean = false
    ): RecipeDetailResponse

    companion object {
        @JvmStatic
        operator fun invoke(chuckerInterceptor: ChuckerInterceptor): RecipeApiService {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(chuckerInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL_RECIPE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(RecipeApiService::class.java)
        }
    }
}