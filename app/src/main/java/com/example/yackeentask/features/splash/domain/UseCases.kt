package com.example.yackeentask.features.splash.domain

import com.example.yackeentask.core.entities.responses.RecipesResponse
import com.example.yackeentask.core.entities.ui_model.RecipesUIModel
import io.reactivex.Single

//get Recipes List from Repository
fun getRecipesListUseCase(recipesListRepository:RecipesListRepository): Single<List<RecipesUIModel>> = recipesListRepository.getRecipesList().map { it.map { it.mapToRecipesUIModel() } }

// map data returned from Repository from RecipesResponse to RecipesUIModel
fun RecipesResponse.mapToRecipesUIModel(): RecipesUIModel = RecipesUIModel(
    calories = if (calories.isNullOrEmpty()) "0 g" else calories,
    description = description,
    name = name,
    id = id,
    carbos = carbos,
    country = country,
    difficulty = difficulty,
    fats = if (fats.isNullOrEmpty()) "0 g" else fats,
    headline = headline,
    image = image,
    proteins = proteins,
    thumb = thumb,
    time = time
)