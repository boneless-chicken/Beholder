package com.bonelesschicken.beholder.ui.launch

import android.content.Intent
import com.bonelesschicken.beholder.data.model.User
import com.bonelesschicken.beholder.ui.BaseActivity
import com.bonelesschicken.beholder.ui.login.LoginActivity
import com.bonelesschicken.beholder.ui.main.MainActivity

class LaunchActivity : BaseActivity() {

    override fun updateUI(currentUser: User?) {
        if (currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
