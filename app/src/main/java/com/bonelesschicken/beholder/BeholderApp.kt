package com.bonelesschicken.beholder

import android.app.Application
import com.bonelesschicken.beholder.data.BeholderDatabase
import com.facebook.stetho.Stetho

class BeholderApp: Application() {
    override fun onCreate() {
        super.onCreate()
        BeholderDatabase.invoke(this)
        Stetho.initializeWithDefaults(this)
    }
}