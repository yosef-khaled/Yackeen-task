package com.example.yackeentask.features.splash.domain

import com.example.yackeentask.core.database.AppDatabase
import com.example.yackeentask.core.entities.responses.RecipesResponse
import io.reactivex.Single

class RecipesListRepository(
    private val endPoint: RecipesEndPoint,
    private val localGetWay: AppDatabase
) {

    // manage data first get from remote and if remote return error or no internet get data from local storage
    fun getRecipesList() =
        getRecipesListFromRemote().onErrorResumeNext { getRecipesListFromLocal() }
            .doOnSuccess { localGetWay.recipesDao().saveSubjects(it) }


    // get data from remote
    private fun getRecipesListFromRemote(): Single<List<RecipesResponse>> =
        endPoint.getRecipesList()

    // get data from local storage
    private fun getRecipesListFromLocal(): Single<List<RecipesResponse>> =
        Single.just(localGetWay.recipesDao().loadSubjects())
}