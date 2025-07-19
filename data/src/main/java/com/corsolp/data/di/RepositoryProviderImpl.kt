package com.corsolp.data.di

import android.content.Context
import com.corsolp.data.local.TokenManager
import com.corsolp.data.remote.RetrofitClient
import com.corsolp.data.repository.AuthRepositoryImpl
import com.corsolp.data.repository.RentalRepositoryImpl
import com.corsolp.domain.di.RepositoryProvider

class RepositoryProviderImpl(private val context: Context) : RepositoryProvider {
    companion object {
        lateinit var tokenManager: TokenManager
    }

    // Creiamo un singolo RetrofitClient
    private val retrofitClient by lazy { RetrofitClient(tokenManager) }

    // AuthRepositoryImpl
    override val authRepository by lazy {
        AuthRepositoryImpl(retrofitClient.authApi, tokenManager)
    }

    // RentalRepositoryImpl
    override val rentalRepository by lazy {
        RentalRepositoryImpl(
            rentApi = retrofitClient.rentApi,
            context = context
        )
    }
}