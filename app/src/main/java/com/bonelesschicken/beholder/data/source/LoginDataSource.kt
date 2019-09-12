package com.bonelesschicken.beholder.data.source

import androidx.lifecycle.MutableLiveData
import com.bonelesschicken.beholder.ui.login.LoginResult
import com.google.firebase.auth.FirebaseAuth
import java.io.IOException


/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String, viewModel: MutableLiveData<LoginResult>) {
        try {
            val auth = FirebaseAuth.getInstance()
            auth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        viewModel.value = LoginResult(auth.currentUser, null)
                    } else {
                        viewModel.value = LoginResult(null, it.exception.toString())
                    }
                }
        } catch (e: Throwable) {
            viewModel.value = LoginResult(null, IOException("Error logging in", e).message)
        }
    }

    fun register(username: String, password: String, viewModel: MutableLiveData<LoginResult>) {
        try {
            val auth = FirebaseAuth.getInstance()
            auth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        viewModel.value = LoginResult(auth.currentUser, null)
                    } else {
                        viewModel.value = LoginResult(null, it.exception.toString())
                    }
                }
        } catch (e: Throwable) {
            viewModel.value = LoginResult(null, IOException("Error logging in", e).message)
        }
    }

    fun logout() {
        FirebaseAuth.getInstance().signOut()
    }
}

