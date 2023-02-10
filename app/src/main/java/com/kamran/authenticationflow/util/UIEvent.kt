package com.kamran.authenticationflow.util

sealed class UIEvent {
    object Success: UIEvent()
    data class ShowSnackbar(val message: UIText): UIEvent()
}
