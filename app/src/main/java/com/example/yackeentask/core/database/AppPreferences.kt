package com.example.yackeentask.core.database

import android.content.Context
import android.content.SharedPreferences
import com.example.yackeentask.core.APP_PREFERENCE_NAME


class AppPreferences(context: Context) {
    private val sharedPref: SharedPreferences =context.getSharedPreferences(APP_PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun saveToSharedPreferences(keyName: String, text: String) = sharedPref.edit().putString(keyName, text).apply()

    fun retrieveFromSharedPreferences(keyName: String) = sharedPref.getString(keyName, "")
}


