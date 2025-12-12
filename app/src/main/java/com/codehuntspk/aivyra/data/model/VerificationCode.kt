package com.codehuntspk.aivyra.data.model

import com.google.gson.annotations.SerializedName

data class VerificationCode(
    @SerializedName("id")
    val id: String,

    @SerializedName("code")
    val code: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("userId")
    val userId: String? = null,

    @SerializedName("type")
    val type: CodeType = CodeType.EMAIL_VERIFICATION,

    @SerializedName("expiresAt")
    val expiresAt: String,

    @SerializedName("used")
    val used: Boolean = false,

    @SerializedName("createdAt")
    val createdAt: String
)

enum class CodeType {
    @SerializedName("EMAIL_VERIFICATION")
    EMAIL_VERIFICATION,

    @SerializedName("PASSWORD_RESET")
    PASSWORD_RESET
}

// Request models for verification
data class SendVerificationCodeRequest(
    @SerializedName("email")
    val email: String,

    @SerializedName("type")
    val type: CodeType = CodeType.EMAIL_VERIFICATION
)

data class VerifyCodeRequest(
    @SerializedName("email")
    val email: String,

    @SerializedName("code")
    val code: String,

    @SerializedName("type")
    val type: CodeType = CodeType.EMAIL_VERIFICATION
)

