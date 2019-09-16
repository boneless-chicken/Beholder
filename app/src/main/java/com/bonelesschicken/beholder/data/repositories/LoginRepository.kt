package com.bonelesschicken.beholder.data.repositories

import androidx.lifecycle.MutableLiveData
import com.bonelesschicken.beholder.data.model.User
import com.bonelesschicken.beholder.data.source.LoginDataSource
import com.bonelesschicken.beholder.network.ApiClient
import com.bonelesschicken.beholder.ui.login.LoginResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

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
        try {
            val auth = FirebaseAuth.getInstance()
            auth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        auth.currentUser?.let { it1 -> createUser(it1, viewModel) }
                    } else {
                        viewModel.value = LoginResult(null, it.exception.toString())
                    }
                }
        } catch (e: Throwable) {
            viewModel.value = LoginResult(null, IOException("Error logging in", e).message)
        }
        //dataSource.register(username, password, viewModel)
    }

    private fun createUser(currentUser: FirebaseUser, viewModel: MutableLiveData<LoginResult>) {
        ApiClient().service.createUser(User("", uid = currentUser.uid, characterList = arrayListOf()))
            .enqueue(
                object : Callback<User> {
                    override fun onFailure(call: Call<User>, t: Throwable) {
                        viewModel.value = LoginResult(null, error = t.toString())
                    }

                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if (response.isSuccessful) {
                            viewModel.value = LoginResult(currentUser, null)
                        } else {
                            viewModel.value = LoginResult(null, IOException("Error logging in").message)
                        }
                    }
            })
    }
}
