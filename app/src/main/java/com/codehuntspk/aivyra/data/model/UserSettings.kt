package com.codehuntspk.aivyra.data.model

import com.google.gson.annotations.SerializedName

data class UserSettings(
    @SerializedName("id")
    val id: String,

    @SerializedName("userId")
    val userId: String,

    // Appearance Settings
    @SerializedName("theme")
    val theme: String = "dark",

    @SerializedName("fontSize")
    val fontSize: Int = 16,

    @SerializedName("compactMode")
    val compactMode: Boolean = false,

    // Notification Settings
    @SerializedName("emailNotifications")
    val emailNotifications: Boolean = true,

    @SerializedName("pushNotifications")
    val pushNotifications: Boolean = false,

    @SerializedName("soundEnabled")
    val soundEnabled: Boolean = true,

    @SerializedName("desktopNotifications")
    val desktopNotifications: Boolean = false,

    // Privacy Settings
    @SerializedName("showOnlineStatus")
    val showOnlineStatus: Boolean = true,

    @SerializedName("allowAnalytics")
    val allowAnalytics: Boolean = true,

    @SerializedName("shareUsageData")
    val shareUsageData: Boolean = false,

    // Chat Settings
    @SerializedName("autoSave")
    val autoSave: Boolean = true,

    @SerializedName("showTimestamps")
    val showTimestamps: Boolean = true,

    @SerializedName("enterToSend")
    val enterToSend: Boolean = true,

    @SerializedName("language")
    val language: String = "en",

    // AI Settings
    @SerializedName("aiModel")
    val aiModel: String = "gemini-2.5-flash",

    @SerializedName("temperature")
    val temperature: Float = 0.7f,

    @SerializedName("maxTokens")
    val maxTokens: Int = 2048,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String
)

data class UpdateUserSettingsRequest(
    // Appearance Settings
    @SerializedName("theme")
    val theme: String? = null,

    @SerializedName("fontSize")
    val fontSize: Int? = null,

    @SerializedName("compactMode")
    val compactMode: Boolean? = null,

    // Notification Settings
    @SerializedName("emailNotifications")
    val emailNotifications: Boolean? = null,

    @SerializedName("pushNotifications")
    val pushNotifications: Boolean? = null,

    @SerializedName("soundEnabled")
    val soundEnabled: Boolean? = null,

    @SerializedName("desktopNotifications")
    val desktopNotifications: Boolean? = null,

    // Privacy Settings
    @SerializedName("showOnlineStatus")
    val showOnlineStatus: Boolean? = null,

    @SerializedName("allowAnalytics")
    val allowAnalytics: Boolean? = null,

    @SerializedName("shareUsageData")
    val shareUsageData: Boolean? = null,

    // Chat Settings
    @SerializedName("autoSave")
    val autoSave: Boolean? = null,

    @SerializedName("showTimestamps")
    val showTimestamps: Boolean? = null,

    @SerializedName("enterToSend")
    val enterToSend: Boolean? = null,

    @SerializedName("language")
    val language: String? = null,

    // AI Settings
    @SerializedName("aiModel")
    val aiModel: String? = null,

    @SerializedName("temperature")
    val temperature: Float? = null,

    @SerializedName("maxTokens")
    val maxTokens: Int? = null
)

