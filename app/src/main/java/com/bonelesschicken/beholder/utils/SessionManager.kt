package com.bonelesschicken.beholder.utils

import android.content.Context
import android.os.AsyncTask
import com.bonelesschicken.beholder.data.BeholderDatabase
import com.bonelesschicken.beholder.data.model.User
import com.google.firebase.auth.FirebaseAuth

class SessionManager(val context: Context) {
    var user: User? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        user = PreferenceManager.loadSession(context)
    }

    fun logout() {
        user = null
        PreferenceManager.clearSession(context)
        FirebaseAuth.getInstance().signOut()
        AsyncTask.execute {
            BeholderDatabase.invoke(context).clearAllTables()
        }
    }
}