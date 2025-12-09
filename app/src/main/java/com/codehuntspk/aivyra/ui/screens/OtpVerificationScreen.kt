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
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpVerificationScreen(
    email: String = "alex.jordan@gmail.com",
    onVerificationSuccess: () -> Unit,
    onNavigateBack: () -> Unit
) {
    var otp1 by remember { mutableStateOf("") }
    var otp2 by remember { mutableStateOf("") }
    var otp3 by remember { mutableStateOf("") }
    var otp4 by remember { mutableStateOf("") }
    var otp5 by remember { mutableStateOf("") }
    var otp6 by remember { mutableStateOf("") }

    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var resendTimer by remember { mutableStateOf(60) }
    var canResend by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current
    val focusRequester1 = remember { FocusRequester() }
    val focusRequester2 = remember { FocusRequester() }
    val focusRequester3 = remember { FocusRequester() }
    val focusRequester4 = remember { FocusRequester() }
    val focusRequester5 = remember { FocusRequester() }
    val focusRequester6 = remember { FocusRequester() }

    val coroutineScope = rememberCoroutineScope()

    // Timer effect
    LaunchedEffect(Unit) {
        while (resendTimer > 0) {
            delay(1000)
            resendTimer--
        }
        canResend = true
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9FAFB))
    ) {
        // OTP Verification Form
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
                            onClick = onNavigateBack,
                            modifier = Modifier.size(40.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Verify Your Email ✉️",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "We've sent a 6-digit verification code to",
                            color = Color.White,
                            fontSize = 16.sp,
                            lineHeight = 24.sp
                        )
                        Text(
                            text = email,
                            color = Color(0xFF9333EA),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
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
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        // OTP Input Fields
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Enter Verification Code",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                OtpTextField(
                                    value = otp1,
                                    onValueChange = {
                                        if (it.length <= 1) {
                                            otp1 = it
                                            if (it.isNotEmpty()) {
                                                focusRequester2.requestFocus()
                                            }
                                        }
                                    },
                                    focusRequester = focusRequester1,
                                    onNext = { focusRequester2.requestFocus() },
                                    modifier = Modifier.weight(1f)
                                )
                                OtpTextField(
                                    value = otp2,
                                    onValueChange = {
                                        if (it.length <= 1) {
                                            otp2 = it
                                            if (it.isNotEmpty()) {
                                                focusRequester3.requestFocus()
                                            } else if (it.isEmpty()) {
                                                focusRequester1.requestFocus()
                                            }
                                        }
                                    },
                                    focusRequester = focusRequester2,
                                    onNext = { focusRequester3.requestFocus() },
                                    modifier = Modifier.weight(1f)
                                )
                                OtpTextField(
                                    value = otp3,
                                    onValueChange = {
                                        if (it.length <= 1) {
                                            otp3 = it
                                            if (it.isNotEmpty()) {
                                                focusRequester4.requestFocus()
                                            } else if (it.isEmpty()) {
                                                focusRequester2.requestFocus()
                                            }
                                        }
                                    },
                                    focusRequester = focusRequester3,
                                    onNext = { focusRequester4.requestFocus() },
                                    modifier = Modifier.weight(1f)
                                )
                                OtpTextField(
                                    value = otp4,
                                    onValueChange = {
                                        if (it.length <= 1) {
                                            otp4 = it
                                            if (it.isNotEmpty()) {
                                                focusRequester5.requestFocus()
                                            } else if (it.isEmpty()) {
                                                focusRequester3.requestFocus()
                                            }
                                        }
                                    },
                                    focusRequester = focusRequester4,
                                    onNext = { focusRequester5.requestFocus() },
                                    modifier = Modifier.weight(1f)
                                )
                                OtpTextField(
                                    value = otp5,
                                    onValueChange = {
                                        if (it.length <= 1) {
                                            otp5 = it
                                            if (it.isNotEmpty()) {
                                                focusRequester6.requestFocus()
                                            } else if (it.isEmpty()) {
                                                focusRequester4.requestFocus()
                                            }
                                        }
                                    },
                                    focusRequester = focusRequester5,
                                    onNext = { focusRequester6.requestFocus() },
                                    modifier = Modifier.weight(1f)
                                )
                                OtpTextField(
                                    value = otp6,
                                    onValueChange = {
                                        if (it.length <= 1) {
                                            otp6 = it
                                            if (it.isEmpty()) {
                                                focusRequester5.requestFocus()
                                            }
                                        }
                                    },
                                    focusRequester = focusRequester6,
                                    onNext = { focusManager.clearFocus() },
                                    modifier = Modifier.weight(1f),
                                    isLast = true
                                )
                            }
                        }

                        // Resend Code Section
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (!canResend) {
                                Text(
                                    text = "Resend code in ${resendTimer}s",
                                    color = Color.White.copy(alpha = 0.7f),
                                    fontSize = 14.sp
                                )
                            } else {
                                Text(
                                    text = "Didn't receive code?  ",
                                    color = Color.White,
                                    fontSize = 14.sp
                                )
                                TextButton(
                                    onClick = {
                                        // TODO: Implement resend OTP logic
                                        resendTimer = 60
                                        canResend = false
                                        coroutineScope.launch {
                                            while (resendTimer > 0) {
                                                delay(1000)
                                                resendTimer--
                                            }
                                            canResend = true
                                        }
                                    },
                                    contentPadding = PaddingValues(0.dp)
                                ) {
                                    Text(
                                        text = "Resend",
                                        fontSize = 14.sp,
                                        color = Color(0xFF9333EA),
                                        fontWeight = FontWeight.Medium,
                                        textDecoration = TextDecoration.Underline
                                    )
                                }
                            }
                        }

                        // Verify Button
                        Button(
                            onClick = {
                                errorMessage = null

                                val otpCode = "$otp1$otp2$otp3$otp4$otp5$otp6"

                                // Validation
                                if (otpCode.length != 6) {
                                    errorMessage = "Please enter all 6 digits"
                                    return@Button
                                }

                                if (!otpCode.all { it.isDigit() }) {
                                    errorMessage = "Please enter valid digits only"
                                    return@Button
                                }

                                isLoading = true

                                // TODO: Implement OTP verification logic
                                // Simulate API call
                                coroutineScope.launch {
                                    delay(1500)
                                    isLoading = false
                                    // onVerificationSuccess()
                                }
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
                                text = if (isLoading) "Verifying..." else "Verify Code",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpTextField(
    value: String,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester,
    onNext: () -> Unit,
    modifier: Modifier = Modifier,
    isLast: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .height(64.dp)
            .focusRequester(focusRequester),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFFE5E7EB),
            focusedContainerColor = Color(0xFFE5E7EB),
            unfocusedBorderColor = Color(0xFF9CA3AF),
            focusedBorderColor = Color(0xFF9333EA),
            unfocusedTextColor = Color(0xFF111827),
            focusedTextColor = Color(0xFF111827)
        ),
        shape = RoundedCornerShape(8.dp),
        textStyle = LocalTextStyle.current.copy(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = if (isLast) ImeAction.Done else ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = { onNext() },
            onDone = { onNext() }
        ),
        singleLine = true
    )
}


@Preview(showBackground = true, widthDp = 400, heightDp = 700)
@Composable
fun OtpVerificationScreenPreview() {
    OtpVerificationScreen(
        email = "alex.jordan@gmail.com",
        onVerificationSuccess = { /* TODO: Implement navigation after verification */ },
        onNavigateBack = { /* TODO: Implement navigation back */ }
    )
}

