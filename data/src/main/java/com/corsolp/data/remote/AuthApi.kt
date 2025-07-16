package com.corsolp.data.remote

import com.corsolp.data.remote.models.auth.AuthResponse
import com.corsolp.data.remote.models.auth.LoginRequest
import com.corsolp.data.remote.models.auth.RegisterRequest
import com.corsolp.data.remote.models.auth.GetAuthProfile
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {
    @Headers("Content-Type: application/json")
    @POST("/api/auth/register")
    suspend fun register(@Body request: RegisterRequest): AuthResponse

    @Headers("Content-Type: application/json")
    @POST("/api/auth/login")
    suspend fun login(@Body request: LoginRequest): AuthResponse

    @POST("/api/auth/logout")
    suspend fun postLogout(): Int

    @GET("/api/auth/profile")
    suspend fun getAuthProfile(): GetAuthProfile
}