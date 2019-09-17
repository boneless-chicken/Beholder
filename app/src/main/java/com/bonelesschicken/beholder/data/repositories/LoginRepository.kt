package com.bonelesschicken.beholder.data.repositories

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData
import com.bonelesschicken.beholder.data.BeholderDatabase
import com.bonelesschicken.beholder.data.daos.CharacterDao
import com.bonelesschicken.beholder.data.model.User
import com.bonelesschicken.beholder.network.ApiClient
import com.bonelesschicken.beholder.network.responses.GetCharactersResponse
import com.bonelesschicken.beholder.ui.login.LoginResult
import com.bonelesschicken.beholder.utils.PreferenceManager
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

class LoginRepository(private val context: Context) {
    private var db : BeholderDatabase = BeholderDatabase.invoke(context)
    private var characterDao: CharacterDao

    init {
        characterDao = db.characterDao()
    }


    fun login(username: String, password: String, viewModel: MutableLiveData<LoginResult>) {
        try {
            val auth = FirebaseAuth.getInstance()
            auth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        auth.currentUser?.let { currentUser -> getUser(currentUser, viewModel) }
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
                        auth.currentUser?.let { currentUser -> createUser(currentUser, viewModel) }
                    } else {
                        viewModel.value = LoginResult(null, it.exception.toString())
                    }
                }
        } catch (e: Throwable) {
            viewModel.value = LoginResult(null, IOException("Error logging in", e).message)
        }
    }

    private fun getUser(currentUser: FirebaseUser, viewModel: MutableLiveData<LoginResult>) {
        ApiClient().service.getCharacters(currentUser.uid)
            .enqueue(object : Callback<GetCharactersResponse> {
                override fun onFailure(call: Call<GetCharactersResponse>, t: Throwable) {
                    viewModel.value = LoginResult(null, error = t.toString())
                }

                override fun onResponse(call: Call<GetCharactersResponse>,
                                        response: Response<GetCharactersResponse>) {
                    if (response.isSuccessful) {
                        AsyncTask.execute {
                            response.body()?.characters?.forEach {
                                characterDao.insertAll(it)
                            }
                        }

                        val userResponse = response.body()?.user
                        if (userResponse != null) {
                            userResponse.email = currentUser.email ?: ""
                            userResponse.name = currentUser.displayName ?: ""
                            PreferenceManager.saveSession(userResponse, context)
                            viewModel.value = LoginResult(userResponse, null)
                        } else {
                            FirebaseAuth.getInstance().signOut()
                            viewModel.value = LoginResult(null, "Null response")
                        }
                    } else {
                        viewModel.value = LoginResult(null, "Not successful response")
                    }
                }
            })
    }

    private fun createUser(currentUser: FirebaseUser, viewModel: MutableLiveData<LoginResult>) {
        ApiClient().service.createUser(User("",
            uid = currentUser.uid,
            characterList = arrayListOf(),
            email = (currentUser.email ?: ""),
            name = (currentUser.displayName ?: "")))
            .enqueue(
                object : Callback<User> {
                    override fun onFailure(call: Call<User>, t: Throwable) {
                        viewModel.value = LoginResult(null, error = t.toString())
                    }

                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if (response.isSuccessful) {
                            val userResponse = response.body()
                            if (userResponse != null) {
                                userResponse.email = currentUser.email ?: ""
                                userResponse.name = currentUser.displayName ?: ""
                                PreferenceManager.saveSession(userResponse, context)
                                viewModel.value = LoginResult(userResponse, null)
                            } else {
                                viewModel.value = LoginResult(null, "Null response")
                            }
                        } else {
                            viewModel.value = LoginResult(null, "Not successful response")
                        }
                    }
            })
    }
}
