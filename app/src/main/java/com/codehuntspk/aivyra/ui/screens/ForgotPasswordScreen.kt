package com.codehuntspk.aivyra.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codehuntspk.aivyra.ui.theme.Purple900
import com.codehuntspk.aivyra.ui.theme.Slate900


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    onEmailSent: (String) -> Unit,
    onNavigateToLogin: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var successMessage by remember { mutableStateOf<String?>(null) }

    val focusManager = LocalFocusManager.current

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9FAFB))
    ) {
        // Forgot Password Form
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Slate900,
                            Purple900.copy(alpha = 0.6f),
                            Slate900
                        )
                    )
                )
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
                        // Back Button
                        IconButton(
                            onClick = onNavigateToLogin,
                            modifier = Modifier.size(40.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back to login",
                                tint = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Forgot Password? 🔒",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "Enter your email address and we'll send you a verification code to reset your password.",
                            color = Color.White,
                            fontSize = 16.sp,
                            lineHeight = 24.sp
                        )
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

                    // Success Message
                    successMessage?.let { success ->
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFD1FAE5) // green-100
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = success,
                                color = Color(0xFF065F46), // green-800
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
                                color = Color.White
                            )
                            OutlinedTextField(
                                value = email,
                                onValueChange = {
                                    email = it
                                    errorMessage = null
                                    successMessage = null
                                },
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
                                    unfocusedContainerColor = Color(0xFFE5E7EB),
                                    focusedContainerColor = Color(0xFFE5E7EB),
                                    unfocusedBorderColor = Color(0xFF9CA3AF),
                                    focusedBorderColor = Color(0xFF9333EA),
                                    unfocusedTextColor = Color(0xFF111827),
                                    focusedTextColor = Color(0xFF111827)
                                ),
                                shape = RoundedCornerShape(8.dp),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Email,
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
                                successMessage = null

                                // Validation
                                if (email.isBlank()) {
                                    errorMessage = "Email is required"
                                    return@Button
                                }

                                val emailRegex = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$".toRegex()
                                if (!email.matches(emailRegex)) {
                                    errorMessage = "Please enter a valid email address"
                                    return@Button
                                }

                                isLoading = true

                                // TODO: Implement forgot password logic
                                // Simulate API call
                                successMessage = "Verification code sent! Please check your email."
                                isLoading = false

                                // Navigate to OTP screen after a delay
                                // onEmailSent(email)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF9333EA),
                                disabledContainerColor = Color(0xFF9333EA).copy(alpha = 0.7f)
                            ),
                            shape = RoundedCornerShape(8.dp),
                            enabled = !isLoading
                        ) {
                            Text(
                                text = if (isLoading) "Sending..." else "Send Verification Code",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }

                        // Login Link
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Remember your password?  ",
                                color = Color.White,
                                fontSize = 14.sp
                            )
                            TextButton(
                                onClick = onNavigateToLogin,
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Text(
                                    text = "Sign in",
                                    fontSize = 14.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Medium,
                                    textDecoration = TextDecoration.Underline
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 400, heightDp = 700)
@Composable
fun ForgotPasswordScreenPreview() {
    ForgotPasswordScreen(
        onEmailSent = { /* TODO: Implement navigation to OTP screen */ },
        onNavigateToLogin = { /* TODO: Implement navigation to login */ }
    )
}

