package com.example.yackeentask.features.recipes_list.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yackeentask.R
import com.example.yackeentask.core.entities.ui_model.RecipesUIModel
import kotlinx.android.synthetic.main.item_recipes.view.*

class RecipesListAdapter(
    private val recipesList: List<RecipesUIModel>,
    private val onRecipesSelected: (RecipesUIModel) -> Unit
) : RecyclerView.Adapter<RecipesListAdapter.RecipesListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesListViewHolder =
        parent.context
            .let { LayoutInflater.from(it) }
            .inflate(R.layout.item_recipes, parent, false)
            .let { RecipesListViewHolder(it) }

    override fun getItemCount(): Int = recipesList.size

    override fun onBindViewHolder(holder: RecipesListViewHolder, position: Int) {
        val item = recipesList[position]
        with(holder) {
            Glide.with(itemView.context)
                .load(item.image)
                .into(image)
            name.text = item.name
            calories.text = item.calories
            fat.text = item.fats
            recipesCard.setOnClickListener { onRecipesSelected(item) }
        }
    }

    class RecipesListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.image_view_image
        val name: TextView = view.text_view_name
        val calories: TextView = view.text_view_calories
        val fat: TextView = view.text_view_fat
        val recipesCard: CardView = view.card_view_recipes_card
    }
}