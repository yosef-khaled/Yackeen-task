package com.example.yackeentask.features.splash.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.yackeentask.R
import com.example.yackeentask.core.RECIPES_List
import com.example.yackeentask.core.database.AppDatabase
import com.example.yackeentask.core.entities.ui_model.RecipesUIModel
import com.example.yackeentask.core.network.RxRetrofitClient
import com.example.yackeentask.features.recipes_list.view.MainActivity
import com.example.yackeentask.features.splash.domain.RecipesEndPoint
import com.example.yackeentask.features.splash.domain.RecipesListRepository
import com.example.yackeentask.features.splash.view_model.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_splash.*
import java.io.Serializable

class SplashActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(SplashViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initViewModel()
        initObservers()
    }

    // coll view model to get Recipes List
    private fun initViewModel() {
        viewModel.fetchRecipesList(repository = RecipesListRepository(endPoint = RxRetrofitClient.clientInstant.create(RecipesEndPoint::class.java),localGetWay = AppDatabase.getAppDatabase(this)) )
    }

    // init Observers
    private fun initObservers() {
        viewModel.screenStates.observe(
            this,
            Observer<SplashScreenState> { onScreenStatsChange(it) })
    }

    // observe on SplashScreenState when it changed
    private fun onScreenStatsChange(screenState: SplashScreenState) {
        when (screenState) {
            is Success -> navigateToRecipesListScreen(screenState.recipesList)
            is Loading -> loadingLogic()
            is Error -> showErrorSnackbar(screenState.error)
        }
    }

    // when loading it coll to make progress_bar visible
    private fun loadingLogic() {
        progress_bar.visibility = View.VISIBLE
    }


    // navigate To Recipes List Screen
    private fun navigateToRecipesListScreen(recipesList: List<RecipesUIModel>) {
        startActivity(
            Intent(this, MainActivity::class.java).putExtra(
                RECIPES_List,
                recipesList as Serializable
            )
        )
        finish()

    }

    // when view model emmit error it function show this error massage in  Snackbar
    private fun showErrorSnackbar(errorMasseg: String) {
        progress_bar.visibility = View.GONE
        Snackbar.make(main_view, errorMasseg, Snackbar.LENGTH_LONG).show()
    }

}