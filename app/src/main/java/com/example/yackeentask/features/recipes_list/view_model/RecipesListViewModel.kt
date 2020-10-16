package com.example.yackeentask.features.recipes_list.view_model

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yackeentask.core.CALORIES
import com.example.yackeentask.core.FATS
import com.example.yackeentask.core.entities.ui_model.RecipesUIModel
import com.example.yackeentask.features.recipes_list.domain.*

class RecipesListViewModel(
    private val sortByFatUseCase: (List<RecipesUIModel>, DataSource, Context) -> List<RecipesUIModel> = ::sortByFat,
    private val sortByCaloriesUseCase: (List<RecipesUIModel>, DataSource, Context) -> List<RecipesUIModel> = ::sortByCalories,
    private val searchByNameUseCase: (List<RecipesUIModel>,String) -> List<RecipesUIModel> = ::searchByName,
    private val lastSortedTypeUseCase: (DataSource, Context) -> String? = ::lastSortedType
) : ViewModel() {
    internal var recipesList: List<RecipesUIModel> = listOf()
    internal var searchedList: List<RecipesUIModel> = listOf()
    internal val screenStates by lazy { MutableLiveData<RecipesListScreenStates>() }

    // get recipes List sorted by Fats
    fun getListSortedByFat(dataSource: DataSource = DataSourceImplantation(), context: Context) {
        recipesList = sortByFatUseCase(recipesList, dataSource, context)
        screenStates.postValue(SortedListByFats(recipesList))
    }

    // get recipes List sorted by Calories
    fun getListSortedByCalories(
        dataSource: DataSource = DataSourceImplantation(),
        context: Context
    ) {
        recipesList = sortByCaloriesUseCase(recipesList, dataSource, context)
        screenStates.postValue(SortedListByCalories(recipesList))
    }

    // get last sort type from useCase and coll function to sort by last sort type used
    fun getLastSortedList(dataSource: DataSource = DataSourceImplantation(), context: Context) {
        when (lastSortedTypeUseCase(dataSource, context)) {
            FATS -> getListSortedByFat(context = context)
            CALORIES -> getListSortedByCalories(context = context)
            else -> screenStates.postValue(ListNotSorted(recipesList))
        }


    }

    // get searched List have a search Key
    fun getSearchByName(searchKey:String) {
        searchedList = searchByNameUseCase(recipesList,searchKey)
        if (searchedList.isEmpty()) {
            screenStates.postValue(EmptyScreen)
        } else {
            screenStates.postValue(SearchList(searchedList))
        }
    }

}