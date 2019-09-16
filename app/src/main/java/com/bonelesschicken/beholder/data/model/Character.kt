package com.bonelesschicken.beholder.data.model

import com.google.gson.annotations.SerializedName

data class Character(val id: String,
                     val name: String,
                     val race: String,
                     @SerializedName("class")
                     val characterClass: String,
                     val background: String,
                     val alignment: Alignment,
                     val level: Int,
                     val experiencePoints: Int)