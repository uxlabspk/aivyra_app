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
import com.codehuntspk.aivyra.ui.screens.IntroScreen
import com.codehuntspk.aivyra.ui.screens.LoginScreen
import com.codehuntspk.aivyra.ui.theme.AivyraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AivyraTheme {
                //IntroScreen()
                LoginScreen(
                    onLoginSuccess = {},
                    onNavigateToSignup = {},
                    onNavigateToForgotPassword = {},
                    registered = false, // Pass from navigation args
                    verified = false,
                    resetSuccess = false
                )
            }
        }
    }
}