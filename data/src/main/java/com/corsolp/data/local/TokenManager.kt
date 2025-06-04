package com.corsolp.data.local

import android.content.SharedPreferences
import androidx.core.content.edit

/**
 * Si occupa di salvare/leggere/cancellare il JWT in modo cifrato o semplice.
 * In Clean Architecture, usare ENCRYPTED SharedPreferences (fornite via DI).
 */
class TokenManager(
    private val prefs: SharedPreferences
) {
    private val KEY_JWT = "StudentHome"

    fun saveToken(token: String) {
        prefs.edit { putString(KEY_JWT, token) }
    }

    fun getToken(): String? {
        return prefs.getString(KEY_JWT, null)
    }

    fun clearToken() {
        prefs.edit { remove(KEY_JWT) }
    }
}
