package com.kamran.authenticationflow.use_cases

import java.util.regex.Pattern

class ValidatePassword {
    operator fun invoke(password: String): Boolean {
        return Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$").matcher(password)
            .matches()
    }
}