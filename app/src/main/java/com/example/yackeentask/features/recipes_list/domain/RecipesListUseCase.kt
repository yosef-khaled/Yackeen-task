package com.example.yackeentask.features.recipes_list.domain

import android.content.Context
import com.example.yackeentask.core.CALORIES
import com.example.yackeentask.core.FATS
import com.example.yackeentask.core.entities.ui_model.RecipesUIModel
import java.util.*

// sort by Fat
fun sortByFat(
    recipesList: List<RecipesUIModel>,
    dataSource: DataSource,
    context: Context
): List<RecipesUIModel> {
    dataSource.setLastSortedType(value = FATS, context = context)
    return recipesList.sortedBy { it.fats?.filter { it.isDigit() }?.toInt() }
}

// sort by Calories
fun sortByCalories(
    recipesList: List<RecipesUIModel>,
    dataSource: DataSource,
    context: Context
): List<RecipesUIModel> {
    dataSource.setLastSortedType(value = CALORIES, context = context)
    return recipesList.sortedBy { it.calories?.filter { it.isDigit() }?.toInt() }
}
// give last sort type used
fun lastSortedType(
    dataSource: DataSource,
    context: Context
): String? = dataSource.getLastSortedType(context = context)

// give List of RecipesUIModel who his name include searchKey
fun searchByName(recipesList: List<RecipesUIModel>, searchKey: String): List<RecipesUIModel> =
    recipesList.filter {
        it.name?.toLowerCase(Locale.ROOT)?.contains(searchKey.toLowerCase(Locale.ROOT)) ?: false
    }