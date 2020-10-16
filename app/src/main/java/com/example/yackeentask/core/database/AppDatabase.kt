package com.example.yackeentask.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.yackeentask.core.entities.responses.RecipesResponse
import com.example.yackeentask.features.splash.domain.RecipesDao

@Database(
    entities = [RecipesResponse::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipesDao(): RecipesDao

    companion object {
        fun getAppDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                databaseName
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}

private const val databaseName = "OrcasDatabase"



