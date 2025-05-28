package com.corsolp.studenthome

import android.app.Application
import com.corsolp.data.di.RepositoryProviderImpl
import com.corsolp.domain.di.UseCaseProvider

class MainActivity : Application() {
    override fun onCreate() {
        super.onCreate()

        // Punto di ingresso dell'applicazione
        UseCaseProvider.setup(
            repositoryProvider = RepositoryProviderImpl()
        )
    }
}