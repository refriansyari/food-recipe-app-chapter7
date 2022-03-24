package com.allana.food_recipe_app_chapter7.data.network.services

import com.allana.food_recipe_app_chapter7.BuildConfig
import com.allana.food_recipe_app_chapter7.data.local.preference.datasource.LocalDataAuthSource
import com.allana.food_recipe_app_chapter7.data.network.model.request.AuthRequest
import com.allana.food_recipe_app_chapter7.data.network.model.response.auth.BaseAuthResponse
import com.allana.food_recipe_app_chapter7.data.network.model.response.auth.User
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import java.util.concurrent.TimeUnit

interface AuthApiServices {
    @POST("auth/login")
    suspend fun postLoginUser(@Body loginRequestBody : AuthRequest) : BaseAuthResponse<User, String>

    @POST("auth/register")
    suspend fun postRegisterUser(@Body loginRequestBody : AuthRequest) : BaseAuthResponse<User,String>

    @GET("auth/me")
    suspend fun getSyncUser() : BaseAuthResponse<User,String>

    @GET("users")
    suspend fun getUserData() : BaseAuthResponse<User,String>

    @PUT("users")
    suspend fun putUserData(@Body data : RequestBody) : BaseAuthResponse<User,String>

    companion object {
        @JvmStatic
        operator fun invoke(localDataAuthSource: LocalDataAuthSource, chuckerInterceptor: ChuckerInterceptor) : AuthApiServices {
            val authInterceptor = Interceptor {
                val requestBuilder = it.request().newBuilder()
                localDataAuthSource.getAuthToken()?.let { token ->
                    requestBuilder.addHeader("Authorization", "Bearer $token")
                }
                it.proceed(requestBuilder.build())
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .addInterceptor(chuckerInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL_BINAR_AUTH)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(AuthApiServices::class.java)
        }
    }
}