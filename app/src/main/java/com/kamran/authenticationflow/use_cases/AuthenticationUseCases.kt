package com.kamran.authenticationflow.use_cases

data class AuthenticationUseCases(
    val validateEmail: ValidateEmail,
    val validatePassword: ValidatePassword
)