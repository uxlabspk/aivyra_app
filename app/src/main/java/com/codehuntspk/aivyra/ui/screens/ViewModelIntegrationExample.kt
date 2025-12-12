package com.codehuntspk.aivyra.ui.screens

// Example of how to integrate AuthViewModel with your existing screens
// This is a reference implementation - you can adapt this pattern to your screens

/*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.codehuntspk.aivyra.viewmodel.AuthViewModel
import com.codehuntspk.aivyra.utils.Resource

@Composable
fun LoginScreenWithViewModel(
    viewModel: AuthViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit,
    onNavigateToSignup: () -> Unit,
    onNavigateToForgotPassword: () -> Unit
) {
    val loginState by viewModel.loginState.collectAsState()
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Handle login state changes
    LaunchedEffect(loginState) {
        when (val state = loginState) {
            is Resource.Loading -> {
                isLoading = true
                errorMessage = null
            }
            is Resource.Success -> {
                isLoading = false
                errorMessage = null
                onLoginSuccess()
                viewModel.resetLoginState()
            }
            is Resource.Error -> {
                isLoading = false
                errorMessage = state.message
            }
            null -> {
                isLoading = false
            }
        }
    }

    // Your existing LoginScreen UI
    LoginScreen(
        onLoginSuccess = { email, password ->
            // Call ViewModel instead of direct navigation
            viewModel.login(email, password)
        },
        onNavigateToSignup = onNavigateToSignup,
        onNavigateToForgotPassword = onNavigateToForgotPassword,
        isLoading = isLoading,
        errorMessage = errorMessage
    )
}

// Similar pattern for SignupScreen
@Composable
fun SignupScreenWithViewModel(
    viewModel: AuthViewModel = hiltViewModel(),
    onSignupSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val signupState by viewModel.signupState.collectAsState()
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(signupState) {
        when (val state = signupState) {
            is Resource.Loading -> {
                isLoading = true
                errorMessage = null
            }
            is Resource.Success -> {
                isLoading = false
                errorMessage = null
                onSignupSuccess()
                viewModel.resetSignupState()
            }
            is Resource.Error -> {
                isLoading = false
                errorMessage = state.message
            }
            null -> {
                isLoading = false
            }
        }
    }

    SignupScreen(
        onSignupSuccess = { email, password, fullName ->
            viewModel.signup(email, password, fullName)
        },
        onNavigateToLogin = onNavigateToLogin,
        isLoading = isLoading,
        errorMessage = errorMessage
    )
}

// Chat screen example
@Composable
fun ChatScreenWithViewModel(
    viewModel: ChatViewModel = hiltViewModel()
) {
    val messages by viewModel.messages.collectAsState()
    val sendMessageState by viewModel.sendMessageState.collectAsState()

    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Load chat history on screen start
    LaunchedEffect(Unit) {
        viewModel.loadChatHistory()
    }

    // Handle send message state
    LaunchedEffect(sendMessageState) {
        when (val state = sendMessageState) {
            is Resource.Loading -> {
                isLoading = true
                errorMessage = null
            }
            is Resource.Success -> {
                isLoading = false
                errorMessage = null
                viewModel.resetSendMessageState()
            }
            is Resource.Error -> {
                isLoading = false
                errorMessage = state.message
            }
            null -> {
                isLoading = false
            }
        }
    }

    // Your existing ChatScreen UI
    ChatScreen(
        messages = messages,
        onSendMessage = { message ->
            viewModel.sendMessage(message)
        },
        isLoading = isLoading,
        errorMessage = errorMessage
    )
}
*/

