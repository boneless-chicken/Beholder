package com.bonelesschicken.beholder.ui.login

import com.bonelesschicken.beholder.data.model.User

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: User? = null,
    val error: String? = null
)
