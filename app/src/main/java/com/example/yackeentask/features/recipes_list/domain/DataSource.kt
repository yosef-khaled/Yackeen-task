package com.example.yackeentask.features.recipes_list.domain

import android.content.Context
import com.example.yackeentask.core.SORT_TYPE

interface DataSource {
    fun getLastSortedType(keyName: String  = SORT_TYPE,context: Context) : String?
    fun setLastSortedType(keyName: String  = SORT_TYPE,value:String,context: Context)
}