package com.codehuntspk.aivyra.data.api

import com.codehuntspk.aivyra.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface AivyraApiService {

    // ==================== Authentication Endpoints ====================
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>

    @POST("auth/signup")
    suspend fun signup(@Body request: SignupRequest): Response<AuthResponse>

    @POST("auth/forgot-password")
    suspend fun forgotPassword(@Body request: ForgotPasswordRequest): Response<ApiResponse<Nothing>>

    @POST("auth/verify-otp")
    suspend fun verifyOtp(@Body request: VerifyOtpRequest): Response<ApiResponse<Nothing>>

    @POST("auth/reset-password")
    suspend fun resetPassword(@Body request: ResetPasswordRequest): Response<ApiResponse<Nothing>>

    @GET("auth/me")
    suspend fun getCurrentUser(@Header("Authorization") token: String): Response<ApiResponse<User>>

    @POST("auth/logout")
    suspend fun logout(@Header("Authorization") token: String): Response<ApiResponse<Nothing>>

    // ==================== Verification Endpoints ====================
    @POST("verification/send")
    suspend fun sendVerificationCode(
        @Body request: SendVerificationCodeRequest
    ): Response<ApiResponse<Nothing>>

    @POST("verification/verify")
    suspend fun verifyCode(
        @Body request: VerifyCodeRequest
    ): Response<ApiResponse<Nothing>>

    @POST("verification/resend")
    suspend fun resendVerificationCode(
        @Body request: SendVerificationCodeRequest
    ): Response<ApiResponse<Nothing>>

    // ==================== User Profile Endpoints ====================
    @GET("user/profile")
    suspend fun getUserProfile(
        @Header("Authorization") token: String
    ): Response<ApiResponse<User>>

    @PUT("user/profile")
    suspend fun updateProfile(
        @Header("Authorization") token: String,
        @Body user: User
    ): Response<ApiResponse<User>>

    @PUT("user/password")
    suspend fun changePassword(
        @Header("Authorization") token: String,
        @Body request: Map<String, String>
    ): Response<ApiResponse<Nothing>>

    // ==================== User Settings Endpoints ====================
    @GET("user/settings")
    suspend fun getUserSettings(
        @Header("Authorization") token: String
    ): Response<ApiResponse<UserSettings>>

    @PUT("user/settings")
    suspend fun updateUserSettings(
        @Header("Authorization") token: String,
        @Body request: UpdateUserSettingsRequest
    ): Response<ApiResponse<UserSettings>>

    @POST("user/settings/reset")
    suspend fun resetUserSettings(
        @Header("Authorization") token: String
    ): Response<ApiResponse<UserSettings>>

    // ==================== Conversation Endpoints ====================
    @GET("conversations")
    suspend fun getConversations(
        @Header("Authorization") token: String,
        @Query("isPublic") isPublic: Boolean? = null
    ): Response<ApiResponse<List<Conversation>>>

    @GET("conversations/{conversationId}")
    suspend fun getConversation(
        @Header("Authorization") token: String,
        @Path("conversationId") conversationId: String
    ): Response<ApiResponse<Conversation>>

    @POST("conversations")
    suspend fun createConversation(
        @Header("Authorization") token: String,
        @Body request: CreateConversationRequest
    ): Response<ApiResponse<Conversation>>

    @PUT("conversations/{conversationId}")
    suspend fun updateConversation(
        @Header("Authorization") token: String,
        @Path("conversationId") conversationId: String,
        @Body request: UpdateConversationRequest
    ): Response<ApiResponse<Conversation>>

    @DELETE("conversations/{conversationId}")
    suspend fun deleteConversation(
        @Header("Authorization") token: String,
        @Path("conversationId") conversationId: String
    ): Response<ApiResponse<Nothing>>

    // ==================== Message Endpoints ====================
    @GET("conversations/{conversationId}/messages")
    suspend fun getMessages(
        @Header("Authorization") token: String,
        @Path("conversationId") conversationId: String,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): Response<ApiResponse<List<Message>>>

    @POST("conversations/{conversationId}/messages")
    suspend fun sendMessage(
        @Header("Authorization") token: String,
        @Path("conversationId") conversationId: String,
        @Body request: SendMessageRequest
    ): Response<ApiResponse<Message>>

    @DELETE("messages/{messageId}")
    suspend fun deleteMessage(
        @Header("Authorization") token: String,
        @Path("messageId") messageId: String
    ): Response<ApiResponse<Nothing>>

    // ==================== Shared Conversation Endpoints ====================
    @GET("conversations/{conversationId}/shares")
    suspend fun getConversationShares(
        @Header("Authorization") token: String,
        @Path("conversationId") conversationId: String
    ): Response<ApiResponse<List<SharedConversation>>>

    @POST("conversations/share")
    suspend fun shareConversation(
        @Header("Authorization") token: String,
        @Body request: ShareConversationRequest
    ): Response<ApiResponse<SharedConversation>>

    @PUT("shares/{shareId}/permission")
    suspend fun updateSharePermission(
        @Header("Authorization") token: String,
        @Path("shareId") shareId: String,
        @Body request: Map<String, Permission>
    ): Response<ApiResponse<SharedConversation>>

    @DELETE("shares/{shareId}")
    suspend fun removeShare(
        @Header("Authorization") token: String,
        @Path("shareId") shareId: String
    ): Response<ApiResponse<Nothing>>

    @GET("conversations/shared-with-me")
    suspend fun getSharedWithMeConversations(
        @Header("Authorization") token: String
    ): Response<ApiResponse<List<Conversation>>>

    // ==================== Backward Compatibility Aliases ====================
    @GET("chat/history")
    suspend fun getChatHistory(
        @Header("Authorization") token: String,
        @Query("session_id") sessionId: String? = null
    ): Response<ApiResponse<List<ChatMessage>>>

    @GET("chat/sessions")
    suspend fun getChatSessions(
        @Header("Authorization") token: String
    ): Response<ApiResponse<List<ChatSession>>>

    @DELETE("chat/session/{sessionId}")
    suspend fun deleteSession(
        @Header("Authorization") token: String,
        @Path("sessionId") sessionId: String
    ): Response<ApiResponse<Nothing>>
}

