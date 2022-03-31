package com.allana.food_recipe_app_chapter7.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.allana.food_recipe_app_chapter7.data.local.room.dao.FavoriteRecipeDao
import com.allana.food_recipe_app_chapter7.data.local.room.entity.FavoriteRecipe

@Database(entities = [FavoriteRecipe::class], version = 1, exportSchema = false)
abstract class FavRecipeDatabase : RoomDatabase() {

    abstract fun favoriteRecipeDao() : FavoriteRecipeDao

//    companion object {
//        private const val DB_NAME = "fav_recipe_db"
//
//
//        @Volatile
//        private var INSTANCE: FavRecipeDatabase? = null
//        fun getInstance(context: Context): FavRecipeDatabase {
//            // if the INSTANCE is not null, then return it,
//            // if it is, then create the database
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    FavRecipeDatabase::class.java,
//                    DB_NAME
//                ).build()
//                INSTANCE = instance
//                // return instance
//                instance
//            }
//        }
//    }
}