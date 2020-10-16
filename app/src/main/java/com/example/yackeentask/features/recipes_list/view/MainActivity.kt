package com.example.yackeentask.features.recipes_list.view

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yackeentask.R
import com.example.yackeentask.core.RECIPES_DETAILS
import com.example.yackeentask.core.entities.ui_model.RecipesUIModel
import com.example.yackeentask.features.recipes_details.view.RecipesDetailsActivity
import com.example.yackeentask.features.recipes_list.view.adapter.RecipesListAdapter
import com.example.yackeentask.features.recipes_list.view_model.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), PopupMenu.OnMenuItemClickListener {

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(RecipesListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        initObservers()
        initClicking()
    }

    private fun initClicking() {
        text_view_sort_types.setOnClickListener {
            showMenu(it)
        }
        edit_text_search.setImeActionLabel("",EditorInfo.IME_ACTION_SEARCH)
        edit_text_search.setOnEditorActionListener { v, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_NEXT -> {
                    viewModel.getSearchByName(searchKey = edit_text_search.text.toString())
                }
            }
            false
        }
    }

    private fun initViewModel() {
        with(viewModel) {
            recipesList =
                intent.getSerializableExtra(com.example.yackeentask.core.RECIPES_List) as List<RecipesUIModel>
            getLastSortedList(context = this@MainActivity)
        }
    }

    private fun initObservers() {
        viewModel.screenStates.observe(
            this,
            Observer<RecipesListScreenStates> { onScreenStatsChange(it) })
    }

    private fun onScreenStatsChange(screen: RecipesListScreenStates) {
        when (screen) {
            is EmptyScreen -> handelEmptyScreen()
            is SortedListByCalories -> bindSortedList(
                screen.recipesList,
                getString(R.string.calories_menu)
            )
            is SortedListByFats -> bindSortedList(screen.recipesList, getString(R.string.fats_menu))
            is ListNotSorted -> bindSortedList(screen.recipesList)
            is SearchList -> bindUI(screen.recipesList)
        }
    }

    private fun bindSortedList(
        recipesList: List<RecipesUIModel>,
        sortType: String = getString(R.string.sort_by)
    ) {
        text_view_sort_types.text = sortType
        text_view_sort_types.setCompoundDrawablesRelativeWithIntrinsicBounds(
            0,
            0,
            android.R.drawable.arrow_down_float,
            0
        )
        bindUI(recipesList)
    }

    private fun bindUI(recipesList: List<RecipesUIModel>) {
        recycler_view_recipes.visibility = View.VISIBLE
        text_view_empty_screen.visibility = View.GONE
        initRecycler(recipesList)
    }

    private fun handelEmptyScreen() {
        recycler_view_recipes.visibility = View.GONE
        text_view_empty_screen.visibility = View.VISIBLE
    }

    private fun initRecycler(recipesList: List<RecipesUIModel>) {
        with(recycler_view_recipes) {
            layoutManager = LinearLayoutManager(context)
            adapter = RecipesListAdapter(
                recipesList = recipesList,
                onRecipesSelected = ::navigateToRecipesDetails
            )
        }
    }

    private fun navigateToRecipesDetails(item: RecipesUIModel) {
        startActivity(
            Intent(this, RecipesDetailsActivity::class.java).putExtra(
                RECIPES_DETAILS,
                item
            )
        )
    }

    // when click on sort by this coll for show menu for sort by types
    private fun showMenu(v: View?) {
        val popup = PopupMenu(this, v)
        popup.setOnMenuItemClickListener(this)
        popup.inflate(R.menu.menu_sort_types)
        popup.show()
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_menu_fats -> {
                viewModel.getListSortedByFat(context = this@MainActivity)
                true
            }
            R.id.item_menu_calories -> {
                viewModel.getListSortedByCalories(context = this@MainActivity)
                true
            }
            else -> false
        }
    }

}