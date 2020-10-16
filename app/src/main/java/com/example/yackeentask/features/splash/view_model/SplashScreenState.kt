package com.example.yackeentask.features.splash.view_model

import com.example.yackeentask.core.entities.ui_model.RecipesUIModel

sealed class SplashScreenState
class Error(val error: String) : SplashScreenState()
data class Success(val recipesList :List<RecipesUIModel>) : SplashScreenState()
object Loading : SplashScreenState()