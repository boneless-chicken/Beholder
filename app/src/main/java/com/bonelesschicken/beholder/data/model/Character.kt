package com.bonelesschicken.beholder.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "characters")
data class Character(
    @PrimaryKey(autoGenerate = false)
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "race")
    val race: String,

    @ColumnInfo(name = "characterClass")
    @SerializedName("class")
    val characterClass: String,

    @ColumnInfo(name = "background")
    val background: String,

    @Embedded
    val alignment: Alignment,

    @ColumnInfo(name = "level")
    val level: Int,

    @ColumnInfo(name = "experiencePoints")
    val experiencePoints: Int,

    @ColumnInfo(name = "primaryStats")
    val primaryStats: String)