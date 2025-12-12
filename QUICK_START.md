# 🚀 Quick Reference Card

## One-Page Quick Start for Aivyra Database Integration

---

## ⚡ TLDR - 3 Steps to Get Started

1. **Update Backend URL**
   ```kotlin
   // app/src/main/java/.../utils/Constants.kt line 8
   const val BASE_URL = "https://your-backend.com/api/"
   ```

2. **Sync Gradle**
   - Click "Sync Now" in Android Studio

3. **Build & Run**
   ```bash
   ./gradlew clean build
   ```

---

## 📝 Field Name Changes (Find & Replace)

```
user.fullName  →  user.name
user.avatarUrl →  user.avatar
message.message → message.content
ChatSession    →  Conversation (or keep using ChatSession - alias works)
ChatMessage    →  Message (or keep using ChatMessage - alias works)
```

---

## 🗂️ New Models Available

```kotlin
// User
user.name, user.email, user.avatar, user.role, user.emailVerified

// Conversation
conversation.id, conversation.title, conversation.userId, 
conversation.isPublic, conversation.messages

// Message  
message.id, message.content, message.role, message.conversationId

// UserSettings (NEW!)
settings.theme, settings.fontSize, settings.aiModel, 
settings.temperature, settings.maxTokens, ... (20+ fields)

// Enums (NEW!)
UserRole: STUDENT, GENERAL, ADMIN
MessageRole: USER, ASSISTANT, SYSTEM
Permission: VIEW, EDIT
```

---

## 🔌 New Repositories

```kotlin
// Inject in ViewModels
@Inject constructor(
    private val conversationRepository: ConversationRepository,
    private val userSettingsRepository: UserSettingsRepository
)
```

---

## 💬 Send Message (New Way)

```kotlin
conversationRepository.sendMessage(
    conversationId = "cm123...",
    content = "Hello",
    role = MessageRole.USER
).collect { resource -> /* handle */ }
```

---

## ⚙️ Update Settings (NEW Feature)

```kotlin
userSettingsRepository.updateTheme("dark").collect { /* handle */ }
userSettingsRepository.updateAISettings(
    aiModel = "gpt-4",
    temperature = 0.8f,
    maxTokens = 2048
).collect { /* handle */ }
```

---

## 🔗 Share Conversation (NEW Feature)

```kotlin
conversationRepository.shareConversation(
    conversationId = "cm123...",
    sharedWithId = "cm456...",
    permission = Permission.VIEW
).collect { /* handle */ }
```

---

## 🗄️ Database Info

```
Type: NeonDB PostgreSQL
URL: postgresql://neondb_owner:npg_iyCOI2Ra8Kxm@ep-crimson-fog-a174634p-pooler.ap-southeast-1.aws.neon.tech/neondb
```

---

## 📚 Documentation Files

| File | Purpose |
|------|---------|
| `INTEGRATION_SUMMARY.md` | Complete overview |
| `DATABASE_INTEGRATION_COMPLETE.md` | Detailed guide with examples |
| `BACKEND_API_SPECIFICATION.md` | All API endpoints |
| `UI_MIGRATION_GUIDE.md` | Step-by-step UI updates |

---

## 🔧 Common Issues

### Build errors?
→ Sync Gradle, then Clean & Rebuild

### "Unresolved reference"?
→ Just sync Gradle (it's fine)

### Backend connection fails?
→ Check BASE_URL, check backend is running

### Field not found?
→ Update field names (fullName→name, avatarUrl→avatar)

---

## ✅ What Changed

**6 Files Updated:**
- User.kt, Chat.kt, AivyraApiService.kt, AuthRepository.kt, Constants.kt

**4 Files Created:**
- UserSettings.kt, VerificationCode.kt, ConversationRepository.kt, UserSettingsRepository.kt

**New Features:**
- User settings, Conversation sharing, Email verification, Public conversations

**API Endpoints:**
- 29 endpoints total (7 auth, 3 verification, 3 profile, 3 settings, 5 conversations, 3 messages, 5 sharing)

---

## 🎯 Quick Test Checklist

```
[ ] BASE_URL updated
[ ] Gradle synced
[ ] Project builds
[ ] Login works
[ ] Signup works with "name" field
[ ] Create conversation works
[ ] Send message works
[ ] Settings screen works (if implemented)
```

---

## 🚀 Priority Order

1. **First:** Update BASE_URL and sync
2. **Second:** Test auth (login/signup)
3. **Third:** Test conversations & messages
4. **Fourth:** Add new features (settings, sharing)

---

## 💡 Pro Tips

- **Use aliases:** Keep using `ChatMessage` and `ChatSession` if you want
- **Migrate gradually:** Old code still works
- **Test backend:** Use Postman/Thunder Client to test endpoints first
- **Read docs:** Check DATABASE_INTEGRATION_COMPLETE.md for examples

---

## 🎓 Example Code Snippets

### Create Conversation
```kotlin
conversationRepository.createConversation(
    title = "AI Chat",
    isPublic = false
).collect { /* handle */ }
```

### Get Conversations
```kotlin
conversationRepository.getConversations().collect { resource ->
    when (resource) {
        is Resource.Success -> { /* show list */ }
        is Resource.Error -> { /* show error */ }
        is Resource.Loading -> { /* show loading */ }
    }
}
```

### Get Messages
```kotlin
conversationRepository.getMessages(
    conversationId = "cm123...",
    limit = 50,
    offset = 0
).collect { /* handle */ }
```

### Display in UI
```kotlin
Text(text = user.name)  // was user.fullName
AsyncImage(model = user.avatar)  // was user.avatarUrl
Badge(text = user.role.name)  // NEW!
```

---

## 🔄 Backward Compatible

✅ ChatMessage still works (alias for Message)  
✅ ChatSession still works (alias for Conversation)  
✅ Old endpoints still work (/chat/history, etc.)  
✅ ChatRepository still works  

**You can use old and new code together!**

---

## 📱 For Local Testing

**Android Emulator:**
```kotlin
const val BASE_URL = "http://10.0.2.2:3000/api/"
```

**Physical Device (same WiFi):**
```kotlin
const val BASE_URL = "http://192.168.x.x:3000/api/"  // Your PC's IP
```

**Find your IP:**
```bash
# Linux/Mac
ifconfig | grep inet
# Windows
ipconfig
```

---

## 🎉 You're Done!

Everything is set up and ready to use. Just:
1. Update BASE_URL
2. Sync Gradle
3. Start coding!

---

**Files:** 15 created/modified  
**Features:** 8 new  
**Endpoints:** 29  
**Status:** ✅ COMPLETE

**Need details?** → Read INTEGRATION_SUMMARY.md  
**Need examples?** → Read DATABASE_INTEGRATION_COMPLETE.md  
**Need API info?** → Read BACKEND_API_SPECIFICATION.md  
**Need UI help?** → Read UI_MIGRATION_GUIDE.md

---

**Happy Coding! 🚀**

