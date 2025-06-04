package com.corsolp.data.di

import com.corsolp.data.local.TokenManager
import com.corsolp.data.remote.RetrofitClient
import com.corsolp.data.repository.AuthRepositoryImpl
import com.corsolp.data.repository.RentalRepositoryImpl
import com.corsolp.domain.di.RepositoryProvider

class RepositoryProviderImpl : RepositoryProvider {
    // TokenManager (lo costruisci una volta, magari passandogli SharedPreferences cifrate)
    // Per semplicità, qui ipotizzo che TokenManager sia già stato istanziato altrove (es. in Application).
    private val tokenManager: TokenManager = TokenManager(/* SharedPreferences cifrate */)

    // Creiamo un singolo RetrofitClient
    private val retrofitClient = RetrofitClient(tokenManager)

    // AuthRepositoryImpl
    override val authRepository = AuthRepositoryImpl(
        retrofitClient.authApi,
        tokenManager
    )

    // RentalRepositoryImpl
    override val rentalRepository = RentalRepositoryImpl(
        retrofitClient.rentApi
    )
}