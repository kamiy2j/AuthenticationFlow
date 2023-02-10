package com.kamran.authenticationflow.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.kamran.authenticationflow.use_cases.AuthenticationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationUseCases: AuthenticationUseCases
) : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var isValidEmail by mutableStateOf(true)
    var emailErrorMsg by mutableStateOf("")

    fun onEmailEntered(email: String) {
        this.email = email
        validateEmail()
    }

    fun onPasswordEntered(password: String) {
        this.password = password
    }

    private fun validateEmail() {
        if (authenticationUseCases.validateEmail(email).not()) {
            emailErrorMsg = "Please enter a valid email address"
            isValidEmail = false
        } else {
            emailErrorMsg = ""
            isValidEmail = true
        }
    }

}