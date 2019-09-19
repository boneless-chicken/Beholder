package com.bonelesschicken.beholder.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bonelesschicken.beholder.R
import com.bonelesschicken.beholder.data.model.User
import com.bonelesschicken.beholder.ui.BaseActivity
import com.bonelesschicken.beholder.ui.main.MainActivity
import com.bonelesschicken.beholder.utils.afterTextChanged
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseUser

class LoginActivity : BaseActivity() {
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val usernameLayout = findViewById<TextInputLayout>(R.id.username)
        val passwordLayout = findViewById<TextInputLayout>(R.id.password)
        val username = findViewById<TextInputEditText>(R.id.field_user_id)
        val password = findViewById<TextInputEditText>(R.id.field_password)
        val login = findViewById<MaterialButton>(R.id.login)
        val register = findViewById<MaterialButton>(R.id.register)
        val loading = findViewById<ProgressBar>(R.id.loading)

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory(this)).get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                usernameLayout.error = getString(loginState.usernameError)
                passwordLayout.isErrorEnabled = true
            } else {
                usernameLayout.error = ""
                passwordLayout.isErrorEnabled = false
            }
            if (loginState.passwordError != null) {
                passwordLayout.error = getString(loginState.passwordError)
                passwordLayout.isErrorEnabled = true
            } else {
                passwordLayout.error = ""
                passwordLayout.isErrorEnabled = false
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUI(loginResult.success)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        })

        loginViewModel.registerResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUI(loginResult.success)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE -> {
                        loading.visibility = View.VISIBLE
                        loginViewModel.login(username.text.toString(), password.text.toString())
                    }
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.login(username.text.toString(), password.text.toString())
            }

            register.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.register(username.text.toString(), password.text.toString())
            }
        }
    }

    private fun showLoginFailed(errorString: String) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

    override fun updateUI(currentUser: User?) {
        if (currentUser != null) {
            val welcome = getString(R.string.welcome)
            val displayName = currentUser.email
            Toast.makeText(
                applicationContext,
                "$welcome $displayName",
                Toast.LENGTH_LONG
            ).show()
        }
    }

}
