package com.bonelesschicken.beholder.ui.launch

import android.content.Intent
import android.os.Bundle
import com.bonelesschicken.beholder.ui.BaseActivity
import com.bonelesschicken.beholder.ui.login.LoginActivity
import com.bonelesschicken.beholder.ui.main.MainActivity
import com.google.firebase.auth.FirebaseUser

class LaunchActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
