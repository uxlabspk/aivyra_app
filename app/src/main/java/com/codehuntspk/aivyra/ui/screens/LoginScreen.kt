package com.codehuntspk.aivyra.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onNavigateToSignup: () -> Unit,
    onNavigateToForgotPassword: () -> Unit,
    registered: Boolean = false,
    verified: Boolean = false,
    resetSuccess: Boolean = false
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val focusManager = LocalFocusManager.current

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9FAFB))
    ) {
        // Right Side - Login Form
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(Color(0xFFD1D5DB)) // gray-300
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier.widthIn(max = 448.dp),
                    verticalArrangement = Arrangement.spacedBy(32.dp)
                ) {
                    // Header
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Welcome back",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF111827) // gray-900
                        )
                        Text(
                            text = "Sign in to your account to continue.",
                            color = Color(0xFF1F2937), // gray-800
                            fontSize = 16.sp
                        )
                    }

                    // Success Messages
                    if (registered) {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFD1FAE5) // green-100
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "Account created successfully! Please sign in.",
                                color = Color(0xFF047857), // green-700
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }

                    if (verified) {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFD1FAE5)
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "Email verified successfully! You can now sign in.",
                                color = Color(0xFF047857),
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }

                    if (resetSuccess) {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFD1FAE5)
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "Password reset successfully! You can now sign in with your new password.",
                                color = Color(0xFF047857),
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }

                    // Error Message
                    errorMessage?.let { error ->
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFFEE2E2) // red-100
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = error,
                                color = Color(0xFFB91C1C), // red-700
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }

                    // Form
                    Column(
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        // Email Input
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Email",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF374151) // gray-700
                            )
                            OutlinedTextField(
                                value = email,
                                onValueChange = { email = it },
                                placeholder = { Text("alex.jordan@gmail.com") },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Email,
                                        contentDescription = null,
                                        tint = Color(0xFF111827)
                                    )
                                },
                                modifier = Modifier.fillMaxWidth(),
                                colors = OutlinedTextFieldDefaults.colors(
                                    unfocusedContainerColor = Color(0xFFE5E7EB), // gray-200
                                    focusedContainerColor = Color(0xFFE5E7EB),
                                    unfocusedBorderColor = Color(0xFF9CA3AF), // gray-400
                                    focusedBorderColor = Color(0xFF9333EA), // purple-600
                                    unfocusedTextColor = Color(0xFF111827),
                                    focusedTextColor = Color(0xFF111827)
                                ),
                                shape = RoundedCornerShape(8.dp),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Email,
                                    imeAction = ImeAction.Next
                                ),
                                keyboardActions = KeyboardActions(
                                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                                ),
                                singleLine = true
                            )
                        }

                        // Password Input
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Password",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color(0xFF374151)
                                )
                                TextButton(
                                    onClick = onNavigateToForgotPassword,
                                    contentPadding = PaddingValues(0.dp)
                                ) {
                                    Text(
                                        text = "Forgot password?",
                                        fontSize = 14.sp,
                                        color = Color(0xFF9333EA),
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                            OutlinedTextField(
                                value = password,
                                onValueChange = { password = it },
                                placeholder = { Text("••••••••") },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Lock,
                                        contentDescription = null,
                                        tint = Color(0xFF111827)
                                    )
                                },
                                trailingIcon = {
                                    IconButton(onClick = { showPassword = !showPassword }) {
                                        Icon(
                                            imageVector = if (showPassword) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                            contentDescription = if (showPassword) "Hide password" else "Show password",
                                            tint = Color(0xFF111827)
                                        )
                                    }
                                },
                                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                                modifier = Modifier.fillMaxWidth(),
                                colors = OutlinedTextFieldDefaults.colors(
                                    unfocusedContainerColor = Color(0xFFE5E7EB),
                                    focusedContainerColor = Color(0xFFE5E7EB),
                                    unfocusedBorderColor = Color(0xFF9CA3AF),
                                    focusedBorderColor = Color(0xFF9333EA),
                                    unfocusedTextColor = Color(0xFF111827),
                                    focusedTextColor = Color(0xFF111827)
                                ),
                                shape = RoundedCornerShape(8.dp),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Password,
                                    imeAction = ImeAction.Done
                                ),
                                keyboardActions = KeyboardActions(
                                    onDone = { focusManager.clearFocus() }
                                ),
                                singleLine = true
                            )
                        }

                        // Submit Button
                        Button(
                            onClick = {
                                errorMessage = null

                                // Validation
                                if (email.isBlank() || password.isBlank()) {
                                    errorMessage = "All fields are required"
                                    return@Button
                                }

                                val emailRegex = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$".toRegex()
                                if (!email.matches(emailRegex)) {
                                    errorMessage = "Please enter a valid email address"
                                    return@Button
                                }

                                isLoading = true
                                // TODO: Implement API call
                                // For now, simulate success after 2 seconds
                                // In real implementation, call your API here
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF9333EA), // purple-600
                                disabledContainerColor = Color(0xFF9333EA).copy(alpha = 0.7f)
                            ),
                            shape = RoundedCornerShape(8.dp),
                            enabled = !isLoading
                        ) {
                            Text(
                                text = if (isLoading) "Signing in..." else "Sign in",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }

                        // Signup Link
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Don't have an account? ",
                                color = Color(0xFF4B5563), // gray-600
                                fontSize = 14.sp
                            )
                            TextButton(
                                onClick = onNavigateToSignup,
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Text(
                                    text = "Create one",
                                    fontSize = 14.sp,
                                    color = Color(0xFF9333EA),
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}