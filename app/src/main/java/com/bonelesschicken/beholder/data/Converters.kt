package com.bonelesschicken.beholder.data

import androidx.room.TypeConverter
import com.bonelesschicken.beholder.data.model.TemporaryHitPoints
import com.bonelesschicken.beholder.data.model.spells.SpellSlot
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object Converters {
    val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun stringToSpellSlot(json: String): List<SpellSlot> {
        val type = object : TypeToken<List<SpellSlot>>() {}.type
        return gson.fromJson<List<SpellSlot>>(json, type)
    }

    @TypeConverter
    @JvmStatic
    fun spellSlotToString(list: List<SpellSlot>): String {
        val type = object : TypeToken<List<SpellSlot>>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    @JvmStatic
    fun stringToTemporaryHitPoints(json: String): List<TemporaryHitPoints> {
        val type = object : TypeToken<List<TemporaryHitPoints>>() {}.type
        return gson.fromJson<List<TemporaryHitPoints>>(json, type)
    }

    @TypeConverter
    @JvmStatic
    fun temporaryHitPointsToString(list: List<TemporaryHitPoints>): String {
        val type = object : TypeToken<List<TemporaryHitPoints>>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    @JvmStatic
    fun stringToStrings(json: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson<List<String>>(json, type)
    }

    @TypeConverter
    @JvmStatic
    fun stringsToString(list: List<String>): String {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.toJson(list, type)
    }
}