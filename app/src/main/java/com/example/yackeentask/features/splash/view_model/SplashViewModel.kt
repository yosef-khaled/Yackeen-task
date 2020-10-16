package com.example.yackeentask.features.splash.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yackeentask.core.entities.ui_model.RecipesUIModel
import com.example.yackeentask.features.splash.domain.RecipesEndPoint
import com.example.yackeentask.features.splash.domain.RecipesListRepository
import com.example.yackeentask.features.splash.domain.getRecipesListUseCase
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SplashViewModel(
    private val fetchRecipesListUseCase: (RecipesListRepository) -> Single<List<RecipesUIModel>> = ::getRecipesListUseCase
) : ViewModel() {

    private var disposable: Disposable? = null
    val screenStates by lazy { MutableLiveData<SplashScreenState>() }

    // get Recipes List from UseCase
    fun fetchRecipesList(
        repository: RecipesListRepository,
        subscribeOnSchedulers: Scheduler = Schedulers.io(),
        observeOnSchedulers: Scheduler = AndroidSchedulers.mainThread()
    ) {
        disposable = fetchRecipesListUseCase(repository)
            .subscribeOn(subscribeOnSchedulers)
            .observeOn(observeOnSchedulers)
            .doOnSubscribe {
                screenStates.postValue(Loading)
            }
            .subscribe(
                {
                    screenStates.postValue(Success(it))
                },
                { screenStates.postValue(Error(it.message ?: "")) })

    }

    //when destroy viewModel dispose Rx chanel
    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}