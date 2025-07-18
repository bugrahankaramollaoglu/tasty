package com.bugrahankaramollaoglu.tasty

import android.content.Context

class PreferencesManager(context: Context) {
    private val prefs = context.getSharedPreferences("tasty_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_IS_LOGGED_IN = "key_is_logged_in"
        private const val KEY_USERNAME = "key_username"
    }

    fun setLoggedIn(isLoggedIn: Boolean) {
        prefs.edit().putBoolean(KEY_IS_LOGGED_IN, isLoggedIn).apply()
    }

    fun isLoggedIn(): Boolean {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun setUsername(username: String) {
        prefs.edit().putString(KEY_USERNAME, username).apply()
    }

    fun getUsername(): String? {
        return prefs.getString(KEY_USERNAME, null)
    }

    fun clear() {
        prefs.edit().clear().apply()
    }
}

