package com.example.yackeentask.features.splash.domain

import com.example.yackeentask.core.entities.responses.RecipesResponse
import io.reactivex.Single
import retrofit2.http.GET

interface RecipesEndPoint {

    @GET("android-test/recipes.json")
    fun getRecipesList():Single<List<RecipesResponse>>
}