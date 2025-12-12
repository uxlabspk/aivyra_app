package com.codehuntspk.aivyra.data.model

import com.google.gson.annotations.SerializedName

// Conversation Model (matches Prisma schema)
data class Conversation(
    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String = "New Conversation",

    @SerializedName("userId")
    val userId: String,

    @SerializedName("isPublic")
    val isPublic: Boolean = false,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String,

    @SerializedName("messages")
    val messages: List<Message>? = null
)

// Message Model (matches Prisma schema)
data class Message(
    @SerializedName("id")
    val id: String,

    @SerializedName("content")
    val content: String,

    @SerializedName("role")
    val role: MessageRole,

    @SerializedName("conversationId")
    val conversationId: String,

    @SerializedName("userId")
    val userId: String,

    @SerializedName("createdAt")
    val createdAt: String
)

enum class MessageRole {
    @SerializedName("USER")
    USER,

    @SerializedName("ASSISTANT")
    ASSISTANT,

    @SerializedName("SYSTEM")
    SYSTEM
}

// SharedConversation Model (matches Prisma schema)
data class SharedConversation(
    @SerializedName("id")
    val id: String,

    @SerializedName("conversationId")
    val conversationId: String,

    @SerializedName("sharedWithId")
    val sharedWithId: String,

    @SerializedName("sharedById")
    val sharedById: String? = null,

    @SerializedName("permission")
    val permission: Permission = Permission.VIEW,

    @SerializedName("createdAt")
    val createdAt: String
)

enum class Permission {
    @SerializedName("VIEW")
    VIEW,

    @SerializedName("EDIT")
    EDIT
}

// Request Models
data class SendMessageRequest(
    @SerializedName("content")
    val content: String,

    @SerializedName("conversationId")
    val conversationId: String? = null,

    @SerializedName("role")
    val role: MessageRole = MessageRole.USER
)

data class CreateConversationRequest(
    @SerializedName("title")
    val title: String = "New Conversation",

    @SerializedName("isPublic")
    val isPublic: Boolean = false
)

data class UpdateConversationRequest(
    @SerializedName("title")
    val title: String? = null,

    @SerializedName("isPublic")
    val isPublic: Boolean? = null
)

data class ShareConversationRequest(
    @SerializedName("conversationId")
    val conversationId: String,

    @SerializedName("sharedWithId")
    val sharedWithId: String,

    @SerializedName("permission")
    val permission: Permission = Permission.VIEW
)

// Backward compatibility aliases
typealias ChatMessage = Message
typealias ChatSession = Conversation

