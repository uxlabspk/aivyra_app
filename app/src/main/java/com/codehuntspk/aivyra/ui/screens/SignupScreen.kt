package com.codehuntspk.aivyra.ui.screens

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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codehuntspk.aivyra.ui.theme.Purple900
import com.codehuntspk.aivyra.ui.theme.Slate900


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    onSignupSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var showConfirmPassword by remember { mutableStateOf(false) }
    var acceptTerms by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val focusManager = LocalFocusManager.current

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9FAFB))
    ) {
        // Sign Up Form
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
                        Text(
                            text = "Create an account ✨",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "Join us and start your journey today.",
                            color = Color.White,
                            fontSize = 16.sp
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

                    // Form
                    Column(
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        // Full Name Input
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Full Name",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White
                            )
                            OutlinedTextField(
                                value = fullName,
                                onValueChange = { fullName = it },
                                placeholder = { Text("Alex Jordan") },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Person,
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
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Next
                                ),
                                keyboardActions = KeyboardActions(
                                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                                ),
                                singleLine = true
                            )
                        }

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
                            Text(
                                text = "Password",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White
                            )
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
                                    imeAction = ImeAction.Next
                                ),
                                keyboardActions = KeyboardActions(
                                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                                ),
                                singleLine = true
                            )
                        }

                        // Confirm Password Input
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Confirm Password",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White
                            )
                            OutlinedTextField(
                                value = confirmPassword,
                                onValueChange = { confirmPassword = it },
                                placeholder = { Text("••••••••") },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Lock,
                                        contentDescription = null,
                                        tint = Color(0xFF111827)
                                    )
                                },
                                trailingIcon = {
                                    IconButton(onClick = { showConfirmPassword = !showConfirmPassword }) {
                                        Icon(
                                            imageVector = if (showConfirmPassword) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                            contentDescription = if (showConfirmPassword) "Hide password" else "Show password",
                                            tint = Color(0xFF111827)
                                        )
                                    }
                                },
                                visualTransformation = if (showConfirmPassword) VisualTransformation.None else PasswordVisualTransformation(),
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

                        // Terms & Conditions
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = acceptTerms,
                                onCheckedChange = { acceptTerms = it },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = Color(0xFF9333EA),
                                    uncheckedColor = Color.White,
                                    checkmarkColor = Color.White
                                )
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "I agree to the Terms & Conditions",
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }

                        // Submit Button
                        Button(
                            onClick = {
                                errorMessage = null

                                // Validation
                                if (fullName.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
                                    errorMessage = "All fields are required"
                                    return@Button
                                }

                                if (fullName.length < 2) {
                                    errorMessage = "Please enter your full name"
                                    return@Button
                                }

                                val emailRegex = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$".toRegex()
                                if (!email.matches(emailRegex)) {
                                    errorMessage = "Please enter a valid email address"
                                    return@Button
                                }

                                if (password.length < 8) {
                                    errorMessage = "Password must be at least 8 characters long"
                                    return@Button
                                }

                                if (password != confirmPassword) {
                                    errorMessage = "Passwords do not match"
                                    return@Button
                                }

                                if (!acceptTerms) {
                                    errorMessage = "Please accept the Terms & Conditions"
                                    return@Button
                                }

                                isLoading = true
                                // TODO: Implement actual signup logic here
                                // For now, navigate directly to main screen
                                onSignupSuccess()
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
                                text = if (isLoading) "Creating account..." else "Create account",
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
                                text = "Already have an account?  ",
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
fun SignupScreenPreview() {
    SignupScreen(
        onSignupSuccess = { /* TODO: Implement navigation after signup */ },
        onNavigateToLogin = { /* TODO: Implement navigation to login */ }
    )
}

