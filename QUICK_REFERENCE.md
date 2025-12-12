# 🚀 Quick Reference - NeonDB Integration

## ⚡ TL;DR - Get Started in 5 Minutes

### Step 1: Update BASE_URL
```kotlin
// File: utils/Constants.kt
const val BASE_URL = "https://your-api.com/api/"  // ← YOUR URL HERE
```

### Step 2: Sync Gradle
Android Studio → Click "Sync Now"

### Step 3: Add ViewModel to Screen
```kotlin
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginScreen(
    viewModel: AuthViewModel = hiltViewModel(),  // ← Add this line
    onLoginSuccess: () -> Unit
) {
    val loginState by viewModel.loginState.collectAsState()
    
    // Your existing UI...
    
    Button(onClick = {
        viewModel.login(email, password)  // ← Call ViewModel
    }) { Text("Sign in") }
    
    // Handle state changes
    LaunchedEffect(loginState) {
        when (loginState) {
            is Resource.Success -> onLoginSuccess()
            is Resource.Error -> errorMessage = loginState.message
            is Resource.Loading -> isLoading = true
            null -> {}
        }
    }
}
```

## 📦 What's Included

| Component | File | Purpose |
|-----------|------|---------|
| API Interface | `data/api/AivyraApiService.kt` | All REST endpoints |
| Auth Logic | `data/repository/AuthRepository.kt` | Login/signup/etc |
| Chat Logic | `data/repository/ChatRepository.kt` | Send/receive messages |
| Auth ViewModel | `viewmodel/AuthViewModel.kt` | Auth state |
| Chat ViewModel | `viewmodel/ChatViewModel.kt` | Chat state |
| Token Storage | `data/local/PreferencesManager.kt` | Secure storage |
| Models | `data/model/User.kt` & `Chat.kt` | Data classes |
| DI Setup | `di/NetworkModule.kt` & `AppModule.kt` | Hilt config |

## 🎯 Available ViewModels

### AuthViewModel
```kotlin
viewModel.login(email, password)           // Login
viewModel.signup(email, password, name)    // Signup
viewModel.forgotPassword(email)            // Send OTP
viewModel.verifyOtp(email, otp)            // Verify OTP
viewModel.logout()                         // Logout
```

### ChatViewModel
```kotlin
viewModel.sendMessage(message)             // Send message
viewModel.loadChatHistory()                // Load history
viewModel.loadChatSessions()               // Load sessions
viewModel.deleteSession(sessionId)         // Delete session
```

## 🔧 Backend Endpoints Required

```
POST   /api/auth/login              {email, password}
POST   /api/auth/signup             {email, password, full_name}
POST   /api/auth/forgot-password    {email}
POST   /api/auth/verify-otp         {email, otp}
POST   /api/chat/send               {message, session_id?}
GET    /api/chat/history            ?session_id=optional
GET    /api/chat/sessions
```

## 📊 Response Format

All endpoints return:
```json
{
  "success": true,
  "message": "Operation successful",
  "data": { /* response data */ }
}
```

## 🔐 Authentication Flow

```
1. User clicks "Sign In"
2. viewModel.login(email, password)
3. AuthRepository calls API
4. Backend validates & returns token
5. Token saved to DataStore automatically
6. Navigate to main screen
7. All future requests include token automatically
```

## 🐛 Common Issues & Fixes

| Issue | Fix |
|-------|-----|
| Unresolved reference 'hilt' | Sync Gradle |
| Network timeout | Check BASE_URL |
| 401 Unauthorized | Token expired/invalid |
| CORS error | Enable CORS on backend |
| Build failed | `./gradlew clean build` |

## 📱 Testing Checklist

- [ ] Update BASE_URL in Constants.kt
- [ ] Sync Gradle successfully
- [ ] Run app
- [ ] Open Logcat → Filter "OkHttp"
- [ ] Try login
- [ ] Check network request in Logcat
- [ ] Verify token saved
- [ ] Try chat feature

## 📚 Documentation Files

- **Full Setup**: `SETUP_COMPLETE.md`
- **API Specs**: `NEONDB_INTEGRATION.md`
- **Action Items**: `INTEGRATION_CHECKLIST.md`
- **Code Examples**: `ViewModelIntegrationExample.kt`

## 🎉 You're Ready!

Everything is configured. Just:
1. Update BASE_URL
2. Sync Gradle
3. Build backend
4. Integrate ViewModels
5. Test & deploy!

**Happy coding!** 🚀

