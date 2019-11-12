package com.bonelesschicken.beholder.data

import androidx.room.TypeConverter
import com.bonelesschicken.beholder.data.model.Cost
import com.bonelesschicken.beholder.data.model.TemporaryHitPoints
import com.bonelesschicken.beholder.data.model.spells.ScalingDamage
import com.bonelesschicken.beholder.data.model.spells.SpellComponent
import com.bonelesschicken.beholder.data.model.spells.SpellComponents
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

    @TypeConverter
    @JvmStatic
    fun stringToSpellComponents(json: String): List<SpellComponent> {
        val type = object : TypeToken<List<SpellComponent>>() {}.type
        return gson.fromJson<List<SpellComponent>>(json, type)
    }

    @TypeConverter
    @JvmStatic
    fun spellComponentsToString(list: List<SpellComponent>): String {
        val type = object : TypeToken<List<SpellComponent>>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    @JvmStatic
    fun stringToScalingDamage(json: String): List<ScalingDamage> {
        val type = object : TypeToken<List<ScalingDamage>>() {}.type
        return gson.fromJson<List<ScalingDamage>>(json, type)
    }

    @TypeConverter
    @JvmStatic
    fun scalingDamageToString(list: List<ScalingDamage>): String {
        val type = object : TypeToken<List<ScalingDamage>>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    @JvmStatic
    fun stringToCost(json: String): List<Cost> {
        val type = object : TypeToken<List<Cost>>() {}.type
        return gson.fromJson<List<Cost>>(json, type)
    }

    @TypeConverter
    @JvmStatic
    fun costToString(list: List<Cost>): String {
        val type = object : TypeToken<List<Cost>>() {}.type
        return gson.toJson(list, type)
    }
}