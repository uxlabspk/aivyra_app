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
import com.codehuntspk.aivyra.ui.screens.*
import com.codehuntspk.aivyra.ui.theme.AivyraTheme

object Destinations {
    const val INTRO_ROUTE = "intro"
    const val LOGIN_ROUTE = "login"
    const val SIGNUP_ROUTE = "signup"
    const val FORGOT_PASSWORD_ROUTE = "forgot_password"
    const val OTP_VERIFICATION_ROUTE = "otp_verification"
    const val MAIN_ROUTE = "main"
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
                        IntroScreen(
                            onNavigateToLogin = {
                                navController.navigate(Destinations.LOGIN_ROUTE)
                            }
                        )
                    }

                    composable(Destinations.LOGIN_ROUTE) {
                        LoginScreen(
                            onLoginSuccess = {
                                navController.navigate(Destinations.MAIN_ROUTE) {
                                    popUpTo(Destinations.INTRO_ROUTE) { inclusive = true }
                                }
                            },
                            onNavigateToSignup = {
                                navController.navigate(Destinations.SIGNUP_ROUTE)
                            },
                            onNavigateToForgotPassword = {
                                navController.navigate(Destinations.FORGOT_PASSWORD_ROUTE)
                            }
                        )
                    }

                    composable(Destinations.SIGNUP_ROUTE) {
                        SignupScreen(
                            onSignupSuccess = {
                                navController.navigate(Destinations.MAIN_ROUTE) {
                                    popUpTo(Destinations.INTRO_ROUTE) { inclusive = true }
                                }
                            },
                            onNavigateToLogin = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable(Destinations.FORGOT_PASSWORD_ROUTE) {
                        ForgotPasswordScreen(
                            onEmailSent = { email ->
                                navController.navigate(Destinations.OTP_VERIFICATION_ROUTE)
                            },
                            onNavigateToLogin = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable(Destinations.OTP_VERIFICATION_ROUTE) {
                        OtpVerificationScreen(
                            onVerificationSuccess = {
                                navController.navigate(Destinations.MAIN_ROUTE) {
                                    popUpTo(Destinations.INTRO_ROUTE) { inclusive = true }
                                }
                            },
                            onNavigateBack = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable(Destinations.MAIN_ROUTE) {
                        MainScreen(
                            onLogout = {
                                navController.navigate(Destinations.LOGIN_ROUTE) {
                                    popUpTo(Destinations.INTRO_ROUTE) { inclusive = true }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}