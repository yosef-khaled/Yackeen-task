package com.example.yackeentask.features.recipes_list.domain

import android.content.Context
import com.example.yackeentask.core.database.AppPreferences

class DataSourceImplantation : DataSource {
    override fun getLastSortedType(keyName: String,context: Context): String? =
        AppPreferences(context).retrieveFromSharedPreferences(keyName)

    override fun setLastSortedType(keyName: String, value: String,context: Context) {
        AppPreferences(context).saveToSharedPreferences(keyName = keyName, text = value)
    }

}