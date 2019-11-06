package com.bonelesschicken.beholder.data.model.spells

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class SpellComponents(
    @ColumnInfo(name = "verbal")
    @SerializedName("verval")
    var verbal: Boolean = false,

    @ColumnInfo(name = "somantic")
    @SerializedName("somantic")
    var somantic: Boolean = false,

    @ColumnInfo(name = "material")
    @SerializedName("material")
    var material: Boolean = false,

    @ColumnInfo(name = "components")
    @SerializedName("components")
    var components: List<SpellComponent> = ArrayList()
)