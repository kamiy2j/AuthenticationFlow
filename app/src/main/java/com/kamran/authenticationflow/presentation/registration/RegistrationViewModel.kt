package com.kamran.authenticationflow.presentation.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.kamran.authenticationflow.use_cases.AuthenticationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val authenticationUseCases: AuthenticationUseCases
) : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var isValidEmail by mutableStateOf(true)
    var emailErrorMsg by mutableStateOf("")
    var isValidPassword by mutableStateOf(true)
    var passwordErrorMsg by mutableStateOf("")

    fun onEmailEntered(email: String) {
        this.email = email
        validateEmail()
    }

    fun onPasswordEntered(password: String) {
        this.password = password
        validatePassword()
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

    private fun validatePassword() {
        if (authenticationUseCases.validatePassword(password).not()) {
            passwordErrorMsg = "Minimum eight characters, at least one letter and one number"
            isValidPassword = false
        } else {
            passwordErrorMsg = ""
            isValidPassword = true
        }
    }
}