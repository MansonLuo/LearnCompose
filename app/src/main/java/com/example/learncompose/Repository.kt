package com.example.learncompose

import android.content.Context
import androidx.preference.PreferenceManager

class Repository(
    context: Context
) {
    private val pref = PreferenceManager.getDefaultSharedPreferences(context)

    fun getInt(key: String, default: Int): Int {
        return pref.getInt(key, default)
    }

    fun putInt(key: String, value: Int) {
        pref.edit().putInt(key, value).apply()
    }

    fun getString(key: String, default: String): String {
        return pref.getString(key, default) ?: default
    }

    fun putString(key: String, value: String) {
        pref.edit().putString(key, value).apply()
    }
}