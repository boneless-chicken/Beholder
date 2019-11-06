package com.bonelesschicken.beholder.data.repositories

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData
import com.bonelesschicken.beholder.data.BeholderDatabase
import com.bonelesschicken.beholder.data.daos.CharacterDao
import com.bonelesschicken.beholder.data.daos.SpellDao
import com.bonelesschicken.beholder.data.model.User
import com.bonelesschicken.beholder.network.ApiClient
import com.bonelesschicken.beholder.network.responses.GameData
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
    private var spellDao: SpellDao

    init {
        characterDao = db.characterDao()
        spellDao = db.spellDao()
    }


    fun login(username: String, password: String, viewModel: MutableLiveData<LoginResult>) {
        try {
            val auth = FirebaseAuth.getInstance()
            auth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        auth.currentUser?.let { currentUser ->
                            val user = User("",
                                currentUser.uid,
                                ArrayList(),
                                currentUser.displayName ?: "",
                                currentUser.email ?: "")
                            getGameData(user, viewModel)
                        }
                    } else {
                        viewModel.value = LoginResult(null, it.exception.toString())
                    }
                }
        } catch (e: Throwable) {
            viewModel.value = LoginResult(null, IOException("Error logging in", e).message)
        }
    }

    private fun getGameData(user: User, viewModel: MutableLiveData<LoginResult>) {
        ApiClient().service.getGameData().enqueue(object : Callback<GameData> {
            override fun onFailure(call: Call<GameData>, t: Throwable) {
                viewModel.value = LoginResult(null, error = t.toString())
            }

            override fun onResponse(call: Call<GameData>, response: Response<GameData>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        AsyncTask.execute {
                            response.body()!!.spells.forEach {
                                spellDao.insert(it)
                            }
                        }
                        PreferenceManager.saveSession(user, context)
                        viewModel.value = LoginResult(user, null)
                    } else {
                        viewModel.value = LoginResult(null, "GetGameData: null response")
                    }
                } else {
                    viewModel.value = LoginResult(null, "GetGameData: not successful response")
                }
            }
        })
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
                                viewModel.value = LoginResult(null, "Create user: null response")
                            }
                        } else {
                            viewModel.value = LoginResult(null, "Create user: not successful response")
                        }
                    }
            })
    }
}
