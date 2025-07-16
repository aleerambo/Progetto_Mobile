package com.corsolp.studenthome

import android.app.Application
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.corsolp.data.di.RepositoryProviderImpl
import com.corsolp.data.local.TokenManager
import com.corsolp.domain.di.UseCaseProvider

class MainActivity : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("MainActivity", "onCreate eseguito")

        // 1. Creo o ottiengo una MasterKey per cifrare le prefs
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        // 2. Creo le EncryptedSharedPreferences
        val sharedPrefs = EncryptedSharedPreferences.create(
            "secure_prefs",          // nome file prefs
            masterKeyAlias,          // chiave di cifratura
            this,                    // context
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        // 3. Istanzio il TokenManager con le prefs cifrate
        val tokenManager = TokenManager(sharedPrefs)

        // 4. Inietto il tokenManager nel RetrofitClient all’interno di RepositoryProviderImpl
        RepositoryProviderImpl.tokenManager = tokenManager

        // 5. Setup dei use case
        UseCaseProvider.setup(RepositoryProviderImpl())
    }
}