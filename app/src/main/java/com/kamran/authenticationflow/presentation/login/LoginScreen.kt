package com.kamran.authenticationflow.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.hilt.navigation.compose.hiltViewModel
import com.kamran.authenticationflow.ui.LocalSpacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onRegistrationClicked: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.spaceMedium),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Login Screen",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            TextField(
                value = viewModel.email,
                onValueChange = viewModel::onEmailEntered,
                isError = !viewModel.isValidEmail,
                label = { Text("Email") },
                placeholder = { Text("abc@email.com") },
                modifier = Modifier.padding(top = spacing.spaceMedium)
            )
            if (!viewModel.isValidEmail) {
                Text(
                    text = viewModel.emailErrorMsg,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(top = spacing.spaceExtraSmall)
                )
            }

            TextField(
                value = viewModel.password,
                onValueChange = viewModel::onPasswordEntered,
                label = { Text("Password") },
                placeholder = { Text("your password") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    // Please provide localized description for accessibility services
                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, description)
                    }
                },
                modifier = Modifier.padding(top = spacing.spaceMedium)
            )

            OutlinedButton(
                onClick = { onLoginSuccess() },
                Modifier.padding(top = spacing.spaceMedium)
            )
            {
                Text("Login")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Not a member? ")
            TextButton(onClick = onRegistrationClicked) {
                Text("Register now")
            }
        }
    }
}