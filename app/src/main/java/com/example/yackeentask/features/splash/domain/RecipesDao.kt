package com.example.yackeentask.features.splash.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.yackeentask.core.entities.responses.RecipesResponse

@Dao
interface RecipesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveSubjects(subjects: List<RecipesResponse>)

    @Query("Select * From Recipes")
    fun loadSubjects(): List<RecipesResponse>
}