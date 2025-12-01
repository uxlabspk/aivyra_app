package com.codehuntspk.aivyra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codehuntspk.aivyra.ui.screens.IntroScreen
import com.codehuntspk.aivyra.ui.screens.LoginScreen
import com.codehuntspk.aivyra.ui.theme.AivyraTheme

object Destinations {
    const val INTRO_ROUTE = "intro"
    const val LOGIN_ROUTE = "login"
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AivyraTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Destinations.INTRO_ROUTE) {
                    composable(Destinations.INTRO_ROUTE) {
                        IntroScreen(onNavigateToLogin = { navController.navigate(Destinations.LOGIN_ROUTE) })
                    }
                    composable(Destinations.LOGIN_ROUTE) {
                        LoginScreen(
                            onLoginSuccess = { /* TODO: Implement navigation after login */ },
                            onNavigateToSignup = { /* TODO: Implement navigation to signup */ },
                            onNavigateToForgotPassword = { /* TODO: Implement navigation to forgot password */ },
                        )
                    }
                }
            }
        }
    }
}