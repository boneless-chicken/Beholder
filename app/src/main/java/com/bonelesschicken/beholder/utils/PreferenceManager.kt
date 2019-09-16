package com.bonelesschicken.beholder.utils

import android.content.Context
import com.bonelesschicken.beholder.data.model.User
import java.lang.ref.WeakReference

object PreferenceManager {

    /**
     * Saves the configuration of the theme selected.
     * @param pref boolean with the preference
     */
    fun saveDarkThemeConfig(pref: Boolean, context: Context) {
        val mSharedPref = context.getSharedPreferences("PREFERENCES_BEHOLDER", Context.MODE_PRIVATE)
        val editor = mSharedPref!!.edit()
        editor.putBoolean("DARK_THEME", pref)
        editor.apply()
    }

    /**
     * Loads the dark theme configuration
     * @return a boolean with the configuration
     */
    fun loadDarkThemeConfig(context: Context): Boolean {
        val mSharedPref = context.getSharedPreferences("PREFERENCES_BEHOLDER", Context.MODE_PRIVATE)
        return mSharedPref!!.getBoolean("DARK_THEME", false)
    }

    fun saveSession(user: User, context: Context) {
        val userString = Serializer.serialize(user)

        val mSharedPref = context.getSharedPreferences("PREFERENCES_BEHOLDER", Context.MODE_PRIVATE)
        val editor = mSharedPref!!.edit()
        editor.putString("USER", userString)
        editor.apply()
    }

    fun loadSession(context: Context) : User? {
        val mSharedPref = context.getSharedPreferences("PREFERENCES_BEHOLDER", Context.MODE_PRIVATE)
        val userString = mSharedPref!!.getString("USER", "")
        return if (userString != null && userString.isNotEmpty()) {
            Serializer.deserialize(userString, User::class.java)
        } else {
            null
        }
    }

    fun clearSession(context: Context) {
        val mSharedPref = context.getSharedPreferences("PREFERENCES_BEHOLDER", Context.MODE_PRIVATE)
        val editor = mSharedPref!!.edit()
        editor.clear()
        editor.apply()
    }
}