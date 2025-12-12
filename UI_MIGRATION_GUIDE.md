# Migration Guide: Updating UI Code

This guide helps you update your existing UI code to use the new data models.

## 🔄 Quick Reference: Old vs New

| Old Name | New Name | Notes |
|----------|----------|-------|
| `ChatSession` | `Conversation` | Alias exists for backward compatibility |
| `ChatMessage` | `Message` | Alias exists for backward compatibility |
| `User.fullName` | `User.name` | Field renamed |
| `User.avatarUrl` | `User.avatar` | Field renamed |
| `session_id` | `conversationId` | API parameter renamed |

---

## 📝 Step-by-Step Migration

### 1. Update User Display Names

**Old Code:**
```kotlin
// In your composables
Text(text = user.fullName)
AsyncImage(model = user.avatarUrl)
```

**New Code:**
```kotlin
// Update to use new field names
Text(text = user.name)
AsyncImage(model = user.avatar)
```

**Find & Replace:**
- Find: `user.fullName` → Replace: `user.name`
- Find: `user.avatarUrl` → Replace: `user.avatar`

---

### 2. Update ViewModels

**Old Code:**
```kotlin
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel() {
    
    fun sendMessage(message: String, sessionId: String?) {
        viewModelScope.launch {
            chatRepository.sendMessage(message, sessionId).collect { /* ... */ }
        }
    }
}
```

**New Code (Option 1 - Use New Repository):**
```kotlin
class ChatViewModel @Inject constructor(
    private val conversationRepository: ConversationRepository
) : ViewModel() {
    
    fun sendMessage(conversationId: String, message: String) {
        viewModelScope.launch {
            conversationRepository.sendMessage(
                conversationId = conversationId,
                content = message,
                role = MessageRole.USER
            ).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        // Handle success
                    }
                    is Resource.Error -> {
                        // Handle error
                    }
                    is Resource.Loading -> {
                        // Show loading
                    }
                }
            }
        }
    }
}
```

**New Code (Option 2 - Keep Old Repository):**
```kotlin
// No changes needed! ChatRepository still works
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel() {
    
    fun sendMessage(message: String, sessionId: String?) {
        viewModelScope.launch {
            chatRepository.sendMessage(message, sessionId).collect { /* ... */ }
        }
    }
}
```

---

### 3. Update Chat Screen

**Old Code:**
```kotlin
@Composable
fun ChatScreen(
    messages: List<ChatMessage>,
    onSendMessage: (String) -> Unit
) {
    LazyColumn {
        items(messages) { message ->
            MessageBubble(
                text = message.message,  // Old field
                isUser = message.userId == currentUserId,
                timestamp = message.created_at
            )
        }
    }
}
```

**New Code:**
```kotlin
@Composable
fun ChatScreen(
    messages: List<Message>,  // or ChatMessage (alias works)
    onSendMessage: (String) -> Unit
) {
    LazyColumn {
        items(messages) { message ->
            MessageBubble(
                text = message.content,  // New field name
                isFromUser = message.role == MessageRole.USER,  // Use role
                timestamp = message.createdAt
            )
        }
    }
}
```

---

### 4. Update Session/Conversation List

**Old Code:**
```kotlin
@Composable
fun ChatSessionList(sessions: List<ChatSession>) {
    LazyColumn {
        items(sessions) { session ->
            ChatSessionItem(
                title = session.title,
                lastUpdated = session.updated_at,
                onClick = { /* navigate */ }
            )
        }
    }
}
```

**New Code:**
```kotlin
@Composable
fun ConversationList(conversations: List<Conversation>) {
    LazyColumn {
        items(conversations) { conversation ->
            ConversationItem(
                title = conversation.title,
                lastUpdated = conversation.updatedAt,
                isPublic = conversation.isPublic,  // New field!
                onClick = { /* navigate */ }
            )
        }
    }
}
```

---

### 5. Update Profile Screen

**Old Code:**
```kotlin
@Composable
fun ProfileScreen(user: User) {
    Column {
        AsyncImage(model = user.avatarUrl)
        Text(text = user.fullName)
        Text(text = user.email)
    }
}
```

**New Code:**
```kotlin
@Composable
fun ProfileScreen(user: User) {
    Column {
        AsyncImage(model = user.avatar)
        Text(text = user.name)
        Text(text = user.email)
        
        // New fields available!
        Badge(text = user.role.name)  // STUDENT, GENERAL, ADMIN
        if (user.emailVerified) {
            Icon(Icons.Default.Verified)
        }
    }
}
```

---

### 6. Add New Features

#### A. User Settings Screen (NEW)

```kotlin
@Composable
fun SettingsScreen(
    settings: UserSettings,
    onUpdateSettings: (UpdateUserSettingsRequest) -> Unit
) {
    Column {
        // Theme Settings
        SettingSection(title = "Appearance") {
            ThemeSelector(
                currentTheme = settings.theme,
                onThemeChange = { newTheme ->
                    onUpdateSettings(UpdateUserSettingsRequest(theme = newTheme))
                }
            )
            FontSizeSlider(
                fontSize = settings.fontSize,
                onFontSizeChange = { newSize ->
                    onUpdateSettings(UpdateUserSettingsRequest(fontSize = newSize))
                }
            )
        }
        
        // Notification Settings
        SettingSection(title = "Notifications") {
            SwitchSetting(
                label = "Email Notifications",
                checked = settings.emailNotifications,
                onCheckedChange = { enabled ->
                    onUpdateSettings(
                        UpdateUserSettingsRequest(emailNotifications = enabled)
                    )
                }
            )
            SwitchSetting(
                label = "Push Notifications",
                checked = settings.pushNotifications,
                onCheckedChange = { enabled ->
                    onUpdateSettings(
                        UpdateUserSettingsRequest(pushNotifications = enabled)
                    )
                }
            )
        }
        
        // AI Settings
        SettingSection(title = "AI Configuration") {
            ModelSelector(
                currentModel = settings.aiModel,
                onModelChange = { model ->
                    onUpdateSettings(UpdateUserSettingsRequest(aiModel = model))
                }
            )
            TemperatureSlider(
                temperature = settings.temperature,
                onTemperatureChange = { temp ->
                    onUpdateSettings(UpdateUserSettingsRequest(temperature = temp))
                }
            )
        }
    }
}
```

#### B. Conversation Sharing (NEW)

```kotlin
@Composable
fun ShareConversationDialog(
    conversationId: String,
    onShare: (String, Permission) -> Unit,
    onDismiss: () -> Unit
) {
    var userEmailToShareWith by remember { mutableStateOf("") }
    var selectedPermission by remember { mutableStateOf(Permission.VIEW) }
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Share Conversation") },
        text = {
            Column {
                OutlinedTextField(
                    value = userEmailToShareWith,
                    onValueChange = { userEmailToShareWith = it },
                    label = { Text("User Email") },
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedPermission == Permission.VIEW,
                        onClick = { selectedPermission = Permission.VIEW }
                    )
                    Text("View Only")
                    
                    Spacer(modifier = Modifier.width(16.dp))
                    
                    RadioButton(
                        selected = selectedPermission == Permission.EDIT,
                        onClick = { selectedPermission = Permission.EDIT }
                    )
                    Text("Can Edit")
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                // Get user ID from email first (you'll need an API endpoint for this)
                // Then share
                onShare(conversationId, selectedPermission)
                onDismiss()
            }) {
                Text("Share")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
```

---

### 7. Update Navigation

**Old Code:**
```kotlin
sealed class Screen(val route: String) {
    object Chat : Screen("chat/{sessionId}")
}

// Usage
navController.navigate("chat/${sessionId}")
```

**New Code:**
```kotlin
sealed class Screen(val route: String) {
    object Chat : Screen("chat/{conversationId}")
    object Settings : Screen("settings")  // NEW
    object SharedConversations : Screen("shared")  // NEW
}

// Usage
navController.navigate("chat/${conversationId}")
navController.navigate("settings")
```

---

### 8. Update Sign Up Form

**Old Code:**
```kotlin
@Composable
fun SignUpScreen(onSignUp: (String, String, String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    
    // ... UI code ...
    
    Button(onClick = {
        onSignUp(email, password, fullName)
    }) {
        Text("Sign Up")
    }
}
```

**New Code:**
```kotlin
@Composable
fun SignUpScreen(onSignUp: (String, String, String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }  // Renamed
    
    // ... UI code ...
    
    OutlinedTextField(
        value = name,
        onValueChange = { name = it },
        label = { Text("Name") }  // Update label if needed
    )
    
    Button(onClick = {
        onSignUp(email, password, name)  // Updated parameter
    }) {
        Text("Sign Up")
    }
}
```

---

## 🎨 UI Enhancement Examples

### Add Role Badge

```kotlin
@Composable
fun UserRoleBadge(role: UserRole) {
    val (color, icon) = when (role) {
        UserRole.STUDENT -> Pair(Color.Blue, Icons.Default.School)
        UserRole.GENERAL -> Pair(Color.Gray, Icons.Default.Person)
        UserRole.ADMIN -> Pair(Color.Red, Icons.Default.AdminPanelSettings)
    }
    
    Surface(
        color = color.copy(alpha = 0.2f),
        shape = RoundedCornerShape(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = role.name,
                color = color,
                fontSize = 12.sp
            )
        }
    }
}
```

### Show Message Role

```kotlin
@Composable
fun MessageBubble(message: Message) {
    val isUser = message.role == MessageRole.USER
    val backgroundColor = when (message.role) {
        MessageRole.USER -> MaterialTheme.colorScheme.primaryContainer
        MessageRole.ASSISTANT -> MaterialTheme.colorScheme.secondaryContainer
        MessageRole.SYSTEM -> MaterialTheme.colorScheme.tertiaryContainer
    }
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
    ) {
        Surface(
            color = backgroundColor,
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(text = message.content)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = formatTimestamp(message.createdAt),
                    fontSize = 10.sp,
                    color = Color.Gray
                )
            }
        }
    }
}
```

---

## ✅ Migration Checklist

Use this checklist to track your migration progress:

### Data Models
- [ ] Replace `user.fullName` with `user.name`
- [ ] Replace `user.avatarUrl` with `user.avatar`
- [ ] Update `ChatMessage` references to `Message` (or use alias)
- [ ] Update `ChatSession` references to `Conversation` (or use alias)

### ViewModels
- [ ] Inject new repositories (`ConversationRepository`, `UserSettingsRepository`)
- [ ] Update method parameters (`sessionId` → `conversationId`)
- [ ] Add new ViewModel methods for settings and sharing

### UI Components
- [ ] Update message display to use `message.content`
- [ ] Use `message.role` instead of checking sender
- [ ] Update timestamp fields (`created_at` → `createdAt`)
- [ ] Add role badges where appropriate
- [ ] Add email verification indicators

### New Features
- [ ] Create Settings screen
- [ ] Create Shared Conversations screen
- [ ] Add share conversation button/dialog
- [ ] Add conversation privacy toggle (public/private)
- [ ] Add role indicators in UI

### Navigation
- [ ] Update navigation routes
- [ ] Add new screens to navigation graph
- [ ] Update deep links if applicable

### Testing
- [ ] Test signup with new `name` field
- [ ] Test conversation creation and messaging
- [ ] Test settings persistence
- [ ] Test sharing functionality
- [ ] Test backward compatibility

---

## 🚨 Common Issues & Solutions

### Issue 1: "Unresolved reference 'fullName'"

**Solution:** Find and replace all `fullName` with `name`

```bash
# In terminal
grep -r "fullName" app/src/main/
# Then manually update each occurrence
```

### Issue 2: Messages not displaying

**Solution:** Update field name from `message` to `content`

```kotlin
// Old
Text(text = chatMessage.message)

// New
Text(text = message.content)
```

### Issue 3: Type mismatch with ChatMessage

**Solution:** Either:
1. Use the alias (no change needed)
2. Update type to `Message`

```kotlin
// Option 1: No change (alias works)
fun displayMessage(message: ChatMessage) { ... }

// Option 2: Update type
fun displayMessage(message: Message) { ... }
```

---

## 🎯 Priority Order

Migrate in this order for smoothest transition:

1. **User model** (name, avatar) - Most critical
2. **Message display** (content, role) - Core functionality
3. **ViewModels** (repositories, methods) - Backend integration
4. **Navigation** (routes, params) - App structure
5. **New features** (settings, sharing) - Enhancements

---

## 📞 Need Help?

If you encounter issues:

1. Check `DATABASE_INTEGRATION_COMPLETE.md` for examples
2. Check `BACKEND_API_SPECIFICATION.md` for API details
3. Review error messages - they often point to outdated field names
4. Use Find & Replace for bulk updates

---

Your migration is complete when:
✅ No compilation errors  
✅ All screens display correctly  
✅ Authentication works  
✅ Conversations/messages work  
✅ New features integrated (optional)  

Happy coding! 🚀

