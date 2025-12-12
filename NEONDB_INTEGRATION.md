# NeonDB Backend Integration Guide

## Overview
This Android app is configured to connect to a NeonDB PostgreSQL backend through a REST API. Below is the setup guide and API documentation.

## Backend Setup Required

### 1. Update the Base URL
Edit `app/src/main/java/com/codehuntspk/aivyra/utils/Constants.kt`:
```kotlin
const val BASE_URL = "https://your-neondb-api.com/api/"
```

Replace with your actual NeonDB backend API URL.

### 2. API Endpoints Structure

Your backend should implement these endpoints:

#### Authentication Endpoints

**POST /api/auth/login**
```json
Request:
{
  "email": "user@example.com",
  "password": "password123"
}

Response:
{
  "success": true,
  "message": "Login successful",
  "data": {
    "user": {
      "id": "uuid",
      "email": "user@example.com",
      "full_name": "John Doe",
      "created_at": "2024-01-01T00:00:00Z",
      "avatar_url": null
    },
    "token": "jwt_token_here"
  }
}
```

**POST /api/auth/signup**
```json
Request:
{
  "email": "user@example.com",
  "password": "password123",
  "full_name": "John Doe"
}

Response: Same as login
```

**POST /api/auth/forgot-password**
```json
Request:
{
  "email": "user@example.com"
}

Response:
{
  "success": true,
  "message": "OTP sent to email"
}
```

**POST /api/auth/verify-otp**
```json
Request:
{
  "email": "user@example.com",
  "otp": "123456"
}

Response:
{
  "success": true,
  "message": "OTP verified"
}
```

**POST /api/auth/reset-password**
```json
Request:
{
  "email": "user@example.com",
  "otp": "123456",
  "new_password": "newpassword123"
}

Response:
{
  "success": true,
  "message": "Password reset successful"
}
```

**GET /api/auth/me**
Headers: Authorization: Bearer {token}
```json
Response:
{
  "success": true,
  "data": {
    "id": "uuid",
    "email": "user@example.com",
    "full_name": "John Doe",
    "created_at": "2024-01-01T00:00:00Z"
  }
}
```

#### Chat Endpoints

**POST /api/chat/send**
Headers: Authorization: Bearer {token}
```json
Request:
{
  "message": "Hello AI",
  "session_id": "optional-session-id"
}

Response:
{
  "success": true,
  "data": {
    "id": "message-id",
    "user_id": "user-id",
    "message": "Hello AI",
    "response": "AI response here",
    "created_at": "2024-01-01T00:00:00Z",
    "session_id": "session-id"
  }
}
```

**GET /api/chat/history?session_id={optional}**
Headers: Authorization: Bearer {token}
```json
Response:
{
  "success": true,
  "data": [
    {
      "id": "message-id",
      "user_id": "user-id",
      "message": "Hello",
      "response": "Hi there!",
      "created_at": "2024-01-01T00:00:00Z",
      "session_id": "session-id"
    }
  ]
}
```

**GET /api/chat/sessions**
Headers: Authorization: Bearer {token}
```json
Response:
{
  "success": true,
  "data": [
    {
      "id": "session-id",
      "user_id": "user-id",
      "title": "Chat about AI",
      "created_at": "2024-01-01T00:00:00Z",
      "updated_at": "2024-01-01T00:00:00Z"
    }
  ]
}
```

## Database Schema (NeonDB PostgreSQL)

### Users Table
```sql
CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    avatar_url TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### Chat Sessions Table
```sql
CREATE TABLE chat_sessions (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    title VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### Chat Messages Table
```sql
CREATE TABLE chat_messages (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    session_id UUID REFERENCES chat_sessions(id) ON DELETE CASCADE,
    message TEXT NOT NULL,
    response TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### OTP Table (for password reset)
```sql
CREATE TABLE otp_codes (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email VARCHAR(255) NOT NULL,
    otp VARCHAR(6) NOT NULL,
    expires_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## Android App Architecture

### Layers
1. **UI Layer** - Composable screens
2. **ViewModel Layer** - State management with StateFlow
3. **Repository Layer** - Data operations
4. **API Layer** - Retrofit service interface
5. **Local Storage** - DataStore for token persistence

### Key Components

#### ViewModels
- `AuthViewModel` - Handles authentication operations
- `ChatViewModel` - Handles chat operations

#### Repositories
- `AuthRepository` - Authentication logic
- `ChatRepository` - Chat operations

#### Data Models
- `User`, `LoginRequest`, `SignupRequest`, `AuthResponse`
- `ChatMessage`, `ChatSession`, `SendMessageRequest`

#### Dependency Injection
- Hilt is configured for DI
- Modules: `NetworkModule`, `AppModule`

### Usage in Screens

Example: Using AuthViewModel in LoginScreen
```kotlin
@Composable
fun LoginScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit
) {
    val loginState by viewModel.loginState.collectAsState()
    
    // Handle login button click
    Button(onClick = {
        viewModel.login(email, password)
    }) {
        Text("Login")
    }
    
    // Observe login state
    LaunchedEffect(loginState) {
        when (loginState) {
            is Resource.Success -> onLoginSuccess()
            is Resource.Error -> showError(loginState.message)
            is Resource.Loading -> showLoading()
        }
    }
}
```

## Testing the Integration

1. Set up your NeonDB backend with the endpoints above
2. Update `Constants.BASE_URL` with your API URL
3. Build and run the app
4. Try login/signup - check Logcat for network requests
5. Use the chat feature to test message sending

## Environment Variables (Optional)

For production, consider using BuildConfig for API URLs:

In `app/build.gradle.kts`:
```kotlin
android {
    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://dev-api.com/\"")
        }
        release {
            buildConfigField("String", "BASE_URL", "\"https://prod-api.com/\"")
        }
    }
}
```

## Security Notes

1. Always use HTTPS in production
2. Store JWT tokens securely (DataStore is encrypted)
3. Implement token refresh logic for long sessions
4. Add request/response encryption for sensitive data
5. Implement proper password hashing on backend (bcrypt)
6. Use environment variables for sensitive configs

## Troubleshooting

### Common Issues

1. **Network Error**: Check BASE_URL is correct
2. **401 Unauthorized**: Token expired or invalid
3. **Connection Timeout**: Check backend is running
4. **SSL Error**: Ensure using HTTPS with valid certificate

### Logging

Check OkHttp logs in Logcat:
- Filter by "OkHttp" to see all network requests/responses
- Look for HTTP status codes and response bodies

## Next Steps

1. Implement your NeonDB backend (Node.js/Express, Python/FastAPI, etc.)
2. Deploy to a hosting service (Vercel, Railway, Render)
3. Update the BASE_URL in Constants.kt
4. Test all endpoints
5. Add error handling and retry logic as needed

