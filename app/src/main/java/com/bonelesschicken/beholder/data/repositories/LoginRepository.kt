package com.bonelesschicken.beholder.data.repositories

import androidx.lifecycle.MutableLiveData
import com.bonelesschicken.beholder.data.Result
import com.bonelesschicken.beholder.data.source.LoginDataSource
import com.bonelesschicken.beholder.ui.login.LoginResult
import com.google.firebase.auth.FirebaseUser

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {

    // in-memory cache of the loggedInUser object
    var user: FirebaseUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        user = null
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    fun login(username: String, password: String, viewModel: MutableLiveData<LoginResult>) {
        dataSource.login(username, password, viewModel)
    }

    fun register(username: String, password: String, viewModel: MutableLiveData<LoginResult>) {
        dataSource.register(username, password, viewModel)
    }
}
