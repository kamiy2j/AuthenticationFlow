package com.kamran.authenticationflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kamran.authenticationflow.navigation.Route
import com.kamran.authenticationflow.presentation.home.HomeScreen
import com.kamran.authenticationflow.presentation.login.LoginScreen
import com.kamran.authenticationflow.presentation.registration.RegistrationScreen
import com.kamran.authenticationflow.ui.theme.AuthenticationFlowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AuthenticationFlowTheme {
                val navController = rememberNavController()
                val snackbarHostState = remember { SnackbarHostState() }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { padding ->
                    Column(
                        modifier = Modifier.padding(padding)
                    ) {
                        NavHost(navController = navController, startDestination = Route.LOGIN) {
                            composable(Route.LOGIN) {
                                LoginScreen(
                                    onLoginSuccess = {
                                        navController.navigate(Route.HOME)
                                    },
                                    onRegistrationClicked = {
                                        navController.navigate(Route.REGISTRATION)
                                    }
                                )
                            }
                            composable(Route.REGISTRATION) {
                                RegistrationScreen(
                                    onRegistrationSuccess = {
                                        navController.navigate(Route.HOME)
                                    },
                                    onLoginClicked = {
                                        navController.navigate(Route.LOGIN)
                                    }
                                )
                            }
                            composable(Route.HOME) {
                                HomeScreen(
                                    onLogoutClicked = {
                                        navController.navigate(Route.LOGIN)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}