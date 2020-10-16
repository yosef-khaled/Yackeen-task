package com.example.yackeentask.features.recipes_list.view_model

import com.example.yackeentask.core.entities.ui_model.RecipesUIModel

sealed class RecipesListScreenStates
object EmptyScreen : RecipesListScreenStates()
data class SortedListByFats(val recipesList: List<RecipesUIModel>) : RecipesListScreenStates()
data class SortedListByCalories(val recipesList: List<RecipesUIModel>) : RecipesListScreenStates()
data class ListNotSorted(val recipesList: List<RecipesUIModel>) : RecipesListScreenStates()
data class SearchList(val recipesList: List<RecipesUIModel>) : RecipesListScreenStates()