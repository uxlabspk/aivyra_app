package com.codehuntspk.aivyra.data.repository

import com.codehuntspk.aivyra.data.api.AivyraApiService
import com.codehuntspk.aivyra.data.local.PreferencesManager
import com.codehuntspk.aivyra.data.model.*
import com.codehuntspk.aivyra.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepository @Inject constructor(
    private val apiService: AivyraApiService,
    private val preferencesManager: PreferencesManager
) {

    suspend fun sendMessage(
        message: String,
        conversationId: String,
        sessionId: String? = null
    ): Flow<Resource<Message>> = flow {
        emit(Resource.Loading())
        try {
            val token = preferencesManager.authToken.first()
            if (token != null) {
                val response = apiService.sendMessage(
                    "Bearer $token",
                    conversationId,
                    SendMessageRequest(content = message)
                )
                if (response.isSuccessful && response.body()?.success == true) {
                    val messageData = response.body()?.data
                    if (messageData != null) {
                        emit(Resource.Success(messageData))
                    } else {
                        emit(Resource.Error("Failed to send message"))
                    }
                } else {
                    emit(Resource.Error(response.body()?.message ?: "Failed to send message"))
                }
            } else {
                emit(Resource.Error("Not authenticated"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    suspend fun getChatHistory(sessionId: String? = null): Flow<Resource<List<ChatMessage>>> = flow {
        emit(Resource.Loading())
        try {
            val token = preferencesManager.authToken.first()
            if (token != null) {
                val response = apiService.getChatHistory("Bearer $token", sessionId)
                if (response.isSuccessful && response.body()?.success == true) {
                    val messages = response.body()?.data ?: emptyList()
                    emit(Resource.Success(messages))
                } else {
                    emit(Resource.Error(response.body()?.message ?: "Failed to load history"))
                }
            } else {
                emit(Resource.Error("Not authenticated"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    suspend fun getChatSessions(): Flow<Resource<List<ChatSession>>> = flow {
        emit(Resource.Loading())
        try {
            val token = preferencesManager.authToken.first()
            if (token != null) {
                val response = apiService.getChatSessions("Bearer $token")
                if (response.isSuccessful && response.body()?.success == true) {
                    val sessions = response.body()?.data ?: emptyList()
                    emit(Resource.Success(sessions))
                } else {
                    emit(Resource.Error(response.body()?.message ?: "Failed to load sessions"))
                }
            } else {
                emit(Resource.Error("Not authenticated"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    suspend fun deleteSession(sessionId: String): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        try {
            val token = preferencesManager.authToken.first()
            if (token != null) {
                val response = apiService.deleteSession("Bearer $token", sessionId)
                if (response.isSuccessful && response.body()?.success == true) {
                    emit(Resource.Success("Session deleted successfully"))
                } else {
                    emit(Resource.Error(response.body()?.message ?: "Failed to delete session"))
                }
            } else {
                emit(Resource.Error("Not authenticated"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        }
    }
}

