package com.bonelesschicken.beholder.network.responses

import com.bonelesschicken.beholder.data.model.User
import com.google.gson.annotations.SerializedName

data class GetCharactersResponse(@SerializedName("user") val user: User)