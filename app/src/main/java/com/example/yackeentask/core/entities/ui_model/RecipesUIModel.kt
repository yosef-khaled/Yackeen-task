package com.example.yackeentask.core.entities.ui_model

import java.io.Serializable

data class RecipesUIModel(
    val calories: String?,
    val carbos: String?,
    val country: String?,
    val description: String?,
    val difficulty: Int?,
    val fats: String?,
    val headline: String?,
    val id: String?,
    val image: String?,
    val name: String?,
    val proteins: String?,
    val thumb: String?,
    val time: String?
) : Serializable