package com.bonelesschicken.beholder.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class CharacterInfo(
    @SerializedName("id")
    @ColumnInfo(name = "characterInfo_id")
    val id: String,

    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String,

    @SerializedName("race")
    @ColumnInfo(name = "race")
    val race: String,

    @SerializedName("class")
    @ColumnInfo(name = "class")
    val className: String,

    @SerializedName("background")
    @ColumnInfo(name = "background")
    val background: String,

    @Embedded
    @SerializedName("alignment")
    val alignment: Alignment,

    @SerializedName("level")
    @ColumnInfo(name = "level")
    val level: Int,

    @SerializedName("experiencePoints")
    @ColumnInfo(name = "experiencePoints")
    val experiencePoints: Int)