# Backend API Endpoints Reference

This document describes all API endpoints that your backend should implement to work with the Android app.

## Base URL
```
https://your-backend.com/api
```

## Response Format

All endpoints should return responses in this format:

```json
{
  "success": true|false,
  "message": "Success or error message",
  "data": { ... } // or null
}
```

---

## 🔐 Authentication Endpoints

### 1. Login
**POST** `/auth/login`

**Request:**
```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

**Response:**
```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "user": {
      "id": "cm...",
      "name": "John Doe",
      "email": "user@example.com",
      "avatar": null,
      "role": "GENERAL",
      "emailVerified": false,
      "createdAt": "2024-12-11T...",
      "updatedAt": "2024-12-11T..."
    },
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
  }
}
```

### 2. Signup
**POST** `/auth/signup`

**Request:**
```json
{
  "email": "user@example.com",
  "password": "password123",
  "name": "John Doe"
}
```

**Response:** Same as Login

### 3. Forgot Password
**POST** `/auth/forgot-password`

**Request:**
```json
{
  "email": "user@example.com"
}
```

**Response:**
```json
{
  "success": true,
  "message": "Verification code sent to your email",
  "data": null
}
```

### 4. Verify OTP
**POST** `/auth/verify-otp`

**Request:**
```json
{
  "email": "user@example.com",
  "otp": "123456"
}
```

### 5. Reset Password
**POST** `/auth/reset-password`

**Request:**
```json
{
  "email": "user@example.com",
  "otp": "123456",
  "new_password": "newpassword123"
}
```

### 6. Get Current User
**GET** `/auth/me`

**Headers:**
```
Authorization: Bearer {token}
```

**Response:**
```json
{
  "success": true,
  "data": { /* user object */ }
}
```

### 7. Logout
**POST** `/auth/logout`

**Headers:**
```
Authorization: Bearer {token}
```

---

## ✉️ Verification Endpoints

### 1. Send Verification Code
**POST** `/verification/send`

**Request:**
```json
{
  "email": "user@example.com",
  "type": "EMAIL_VERIFICATION"  // or "PASSWORD_RESET"
}
```

### 2. Verify Code
**POST** `/verification/verify`

**Request:**
```json
{
  "email": "user@example.com",
  "code": "123456",
  "type": "EMAIL_VERIFICATION"
}
```

### 3. Resend Code
**POST** `/verification/resend`

(Same as Send Verification Code)

---

## 👤 User Profile Endpoints

### 1. Get Profile
**GET** `/user/profile`

**Headers:**
```
Authorization: Bearer {token}
```

### 2. Update Profile
**PUT** `/user/profile`

**Headers:**
```
Authorization: Bearer {token}
```

**Request:**
```json
{
  "name": "Updated Name",
  "avatar": "https://example.com/avatar.jpg"
}
```

### 3. Change Password
**PUT** `/user/password`

**Request:**
```json
{
  "currentPassword": "old123",
  "newPassword": "new123"
}
```

---

## ⚙️ User Settings Endpoints

### 1. Get Settings
**GET** `/user/settings`

**Headers:**
```
Authorization: Bearer {token}
```

**Response:**
```json
{
  "success": true,
  "data": {
    "id": "cm...",
    "userId": "cm...",
    "theme": "dark",
    "fontSize": 16,
    "compactMode": false,
    "emailNotifications": true,
    "pushNotifications": false,
    "soundEnabled": true,
    "desktopNotifications": false,
    "showOnlineStatus": true,
    "allowAnalytics": true,
    "shareUsageData": false,
    "autoSave": true,
    "showTimestamps": true,
    "enterToSend": true,
    "language": "en",
    "aiModel": "gemini-2.5-flash",
    "temperature": 0.7,
    "maxTokens": 2048,
    "createdAt": "...",
    "updatedAt": "..."
  }
}
```

### 2. Update Settings
**PUT** `/user/settings`

**Request:** (All fields optional)
```json
{
  "theme": "light",
  "fontSize": 18,
  "pushNotifications": true,
  "aiModel": "gpt-4",
  "temperature": 0.8
}
```

### 3. Reset Settings
**POST** `/user/settings/reset`

---

## 💬 Conversation Endpoints

### 1. Get All Conversations
**GET** `/conversations?isPublic=false`

**Headers:**
```
Authorization: Bearer {token}
```

**Response:**
```json
{
  "success": true,
  "data": [
    {
      "id": "cm...",
      "title": "My Chat",
      "userId": "cm...",
      "isPublic": false,
      "createdAt": "...",
      "updatedAt": "...",
      "messages": null  // or array if included
    }
  ]
}
```

### 2. Get Single Conversation
**GET** `/conversations/{conversationId}`

**Response:**
```json
{
  "success": true,
  "data": {
    "id": "cm...",
    "title": "My Chat",
    "userId": "cm...",
    "isPublic": false,
    "createdAt": "...",
    "updatedAt": "...",
    "messages": [
      {
        "id": "cm...",
        "content": "Hello",
        "role": "USER",
        "conversationId": "cm...",
        "userId": "cm...",
        "createdAt": "..."
      },
      {
        "id": "cm...",
        "content": "Hi! How can I help?",
        "role": "ASSISTANT",
        "conversationId": "cm...",
        "userId": "cm...",
        "createdAt": "..."
      }
    ]
  }
}
```

### 3. Create Conversation
**POST** `/conversations`

**Request:**
```json
{
  "title": "New Chat",
  "isPublic": false
}
```

### 4. Update Conversation
**PUT** `/conversations/{conversationId}`

**Request:**
```json
{
  "title": "Updated Title",
  "isPublic": true
}
```

### 5. Delete Conversation
**DELETE** `/conversations/{conversationId}`

---

## 💬 Message Endpoints

### 1. Get Messages
**GET** `/conversations/{conversationId}/messages?limit=20&offset=0`

**Response:**
```json
{
  "success": true,
  "data": [
    {
      "id": "cm...",
      "content": "Message text",
      "role": "USER",  // USER, ASSISTANT, or SYSTEM
      "conversationId": "cm...",
      "userId": "cm...",
      "createdAt": "..."
    }
  ]
}
```

### 2. Send Message
**POST** `/conversations/{conversationId}/messages`

**Request:**
```json
{
  "content": "Hello, AI!",
  "role": "USER"
}
```

**Response:**
```json
{
  "success": true,
  "data": {
    "id": "cm...",
    "content": "Hello, AI!",
    "role": "USER",
    "conversationId": "cm...",
    "userId": "cm...",
    "createdAt": "..."
  }
}
```

**Note:** Your backend should:
1. Save the user's message
2. Get AI response
3. Save AI response as ASSISTANT role
4. Return the user's message (or both messages)

### 3. Delete Message
**DELETE** `/messages/{messageId}`

---

## 🔗 Sharing Endpoints

### 1. Get Conversation Shares
**GET** `/conversations/{conversationId}/shares`

**Response:**
```json
{
  "success": true,
  "data": [
    {
      "id": "cm...",
      "conversationId": "cm...",
      "sharedWithId": "cm...",
      "sharedById": "cm...",
      "permission": "VIEW",  // VIEW or EDIT
      "createdAt": "..."
    }
  ]
}
```

### 2. Share Conversation
**POST** `/conversations/share`

**Request:**
```json
{
  "conversationId": "cm...",
  "sharedWithId": "cm...",
  "permission": "VIEW"
}
```

### 3. Update Share Permission
**PUT** `/shares/{shareId}/permission`

**Request:**
```json
{
  "permission": "EDIT"
}
```

### 4. Remove Share
**DELETE** `/shares/{shareId}`

### 5. Get Shared With Me
**GET** `/conversations/shared-with-me`

**Response:**
```json
{
  "success": true,
  "data": [
    {
      "id": "cm...",
      "title": "Shared Chat",
      "userId": "cm...",  // Original owner
      "isPublic": false,
      "createdAt": "...",
      "updatedAt": "..."
    }
  ]
}
```

---

## 🔄 Backward Compatibility Endpoints

These are old endpoints that should still work:

### Get Chat History
**GET** `/chat/history?session_id={sessionId}`

### Get Chat Sessions
**GET** `/chat/sessions`

### Delete Session
**DELETE** `/chat/session/{sessionId}`

---

## 🗂️ Database Schema Reference

```prisma
model User {
  id            String   @id @default(cuid())
  name          String
  email         String   @unique
  password      String   // Hashed
  avatar        String?
  role          UserRole @default(GENERAL)
  emailVerified Boolean  @default(false)
  createdAt     DateTime @default(now())
  updatedAt     DateTime @updatedAt
}

model Conversation {
  id        String   @id @default(cuid())
  title     String   @default("New Conversation")
  userId    String
  isPublic  Boolean  @default(false)
  createdAt DateTime @default(now())
  updatedAt DateTime @updatedAt
}

model Message {
  id             String      @id @default(cuid())
  content        String      @db.Text
  role           MessageRole
  conversationId String
  userId         String
  createdAt      DateTime    @default(now())
}

model UserSettings {
  id                   String   @id @default(cuid())
  userId               String   @unique
  // ... (see schema for all fields)
}

model SharedConversation {
  id             String     @id @default(cuid())
  conversationId String
  sharedWithId   String
  sharedById     String?
  permission     Permission @default(VIEW)
  createdAt      DateTime   @default(now())
}

model VerificationCode {
  id        String   @id @default(cuid())
  code      String
  email     String
  userId    String?
  type      CodeType @default(EMAIL_VERIFICATION)
  expiresAt DateTime
  used      Boolean  @default(false)
  createdAt DateTime @default(now())
}

enum UserRole { STUDENT, GENERAL, ADMIN }
enum MessageRole { USER, ASSISTANT, SYSTEM }
enum Permission { VIEW, EDIT }
enum CodeType { EMAIL_VERIFICATION, PASSWORD_RESET }
```

---

## 🔒 Authentication

All endpoints except login/signup require Bearer token:

```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

Generate JWT token on login/signup and verify it on protected routes.

---

## ✅ Implementation Checklist

For your backend, implement:

- [ ] All Auth endpoints
- [ ] User Profile endpoints
- [ ] User Settings endpoints (create default settings on signup)
- [ ] Conversation CRUD
- [ ] Message CRUD with AI integration
- [ ] Sharing functionality
- [ ] Verification code system
- [ ] JWT authentication
- [ ] Input validation
- [ ] Error handling
- [ ] Password hashing (bcrypt)
- [ ] CORS configuration
- [ ] Rate limiting (optional)

---

## 🧪 Testing

Test with tools like:
- **Postman** - API testing
- **cURL** - Command line testing
- **Thunder Client** - VS Code extension

Example cURL:
```bash
curl -X POST https://your-backend.com/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"test@example.com","password":"test123"}'
```

---

## 📚 Resources

- **Prisma Docs:** https://www.prisma.io/docs
- **Express.js:** https://expressjs.com/
- **JWT:** https://jwt.io/
- **NeonDB:** https://neon.tech/docs

Your backend is ready to be built with this specification! 🚀

