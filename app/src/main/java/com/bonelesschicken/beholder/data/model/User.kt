package com.bonelesschicken.beholder.data.model

import com.google.gson.annotations.SerializedName

data class User(@SerializedName("id") val id: String,
                @SerializedName("uid") val uid: String,
                @SerializedName("characterList") val characterList: List<String>,
                var name: String,
                var email: String)