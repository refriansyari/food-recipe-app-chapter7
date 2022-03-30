package com.allana.food_recipe_app_chapter7.data.local.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favRecipe")
data class FavoriteRecipe(
    @PrimaryKey
    var idRecipe: Long? = null,
    @ColumnInfo(name = "name")
    var name: String? = null,
    @ColumnInfo(name = "image")
    var image: String? = null,
//    @ColumnInfo(name = "dish_types")
//    var dishTypes: List<String>? = null
) : Parcelable