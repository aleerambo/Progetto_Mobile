package com.corsolp.data.remote

import com.corsolp.data.local.TokenManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitClient(
    private val tokenManager: TokenManager  // passo qui TokenManager
) {
    private val baseUrl = "http://studenthome.mywire.org:3000"

    // Interceptor per aggiungere Content-Type
    private val headersInterceptor = Interceptor { chain ->
        val request: Request = chain.request()
            .newBuilder()
            .addHeader("Content-Type", "application/json")
            .build()
        chain.proceed(request)
    }

    // Interceptor anonimo per aggiungere Authorization: Bearer <token>
    private val authInterceptor = Interceptor { chain ->
        val originalRequest: Request = chain.request()
        val token: String? = tokenManager.getToken()

        // Log dei dati della richiesta
        val requestBody = originalRequest.body
        if (requestBody != null) {
            val buffer = okio.Buffer()
            requestBody.writeTo(buffer)
            println("Request Body: ${buffer.readUtf8()}")
        }

        // Se non c’è token, procedo come prima
        if (token.isNullOrEmpty()) {
            chain.proceed(originalRequest)
        } else {
            val authorizedRequest: Request = originalRequest
                .newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(authorizedRequest)
        }
    }

    // Costruisco OkHttpClient con entrambi gli interceptor
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(headersInterceptor)  // aggiunge sempre Content-Type
        .addInterceptor(authInterceptor)     // aggiunge il token se esiste
        .build()

    // Moshi per JSON
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    // Instanzio Retrofit
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    // Esposizione dei due API
    val authApi: AuthApi by lazy {
        retrofit.create(AuthApi::class.java)
    }

    val rentApi: RentApi by lazy {
        retrofit.create(RentApi::class.java)
    }
}
