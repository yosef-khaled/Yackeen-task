package com.example.yackeentask.features.recipes_details.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.yackeentask.R
import com.example.yackeentask.core.RECIPES_DETAILS
import com.example.yackeentask.core.entities.ui_model.RecipesUIModel
import kotlinx.android.synthetic.main.activity_recipes_details.*

class RecipesDetailsActivity : AppCompatActivity() {

    private val recipeDetails by lazy { intent.getSerializableExtra(RECIPES_DETAILS) as RecipesUIModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes_details)
        bindUI()
    }

    private fun bindUI() {
        with(recipeDetails) {
            Glide.with(this@RecipesDetailsActivity).load(image).into(image_view_image)
            text_view_name.text = name
            text_view_head_line.text = headline
            text_view_calories.text = calories
            text_view_carbos.text = carbos
            text_view_difficulty.text = difficulty.toString()
            text_view_fats.text = fats
            text_view_proteins.text = proteins
            text_view_time.text = time
            text_view_description.text = description
        }
    }
}