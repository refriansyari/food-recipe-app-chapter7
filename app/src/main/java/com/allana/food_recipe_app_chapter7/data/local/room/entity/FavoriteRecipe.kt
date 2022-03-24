package com.allana.food_recipe_app_chapter7.data.local.room.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favRecipe")
data class FavoriteRecipe(
    @PrimaryKey
    val idRecipe : Int = 0
) : Parcelable