package com.codehuntspk.aivyra.data.repository

import com.codehuntspk.aivyra.data.api.AivyraApiService
import com.codehuntspk.aivyra.data.local.PreferencesManager
import com.codehuntspk.aivyra.data.model.*
import com.codehuntspk.aivyra.utils.Constants
import com.codehuntspk.aivyra.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConversationRepository @Inject constructor(
    private val apiService: AivyraApiService,
    private val preferencesManager: PreferencesManager
) {

    // ==================== Conversation Management ====================

    suspend fun getConversations(isPublic: Boolean? = null): Flow<Resource<List<Conversation>>> = flow {
        emit(Resource.Loading())
        try {
            val token = preferencesManager.authToken.first()
            if (token != null) {
                val response = apiService.getConversations("${Constants.AUTH_HEADER_PREFIX}$token", isPublic)
                if (response.isSuccessful && response.body()?.success == true) {
                    val conversations = response.body()?.data ?: emptyList()
                    emit(Resource.Success(conversations))
                } else {
                    emit(Resource.Error(response.body()?.message ?: "Failed to load conversations"))
                }
            } else {
                emit(Resource.Error("Not authenticated"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    suspend fun getConversation(conversationId: String): Flow<Resource<Conversation>> = flow {
        emit(Resource.Loading())
        try {
            val token = preferencesManager.authToken.first()
            if (token != null) {
                val response = apiService.getConversation("${Constants.AUTH_HEADER_PREFIX}$token", conversationId)
                if (response.isSuccessful && response.body()?.success == true) {
                    val conversation = response.body()?.data
                    if (conversation != null) {
                        emit(Resource.Success(conversation))
                    } else {
                        emit(Resource.Error("Conversation not found"))
                    }
                } else {
                    emit(Resource.Error(response.body()?.message ?: "Failed to load conversation"))
                }
            } else {
                emit(Resource.Error("Not authenticated"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    suspend fun createConversation(
        title: String = "New Conversation",
        isPublic: Boolean = false
    ): Flow<Resource<Conversation>> = flow {
        emit(Resource.Loading())
        try {
            val token = preferencesManager.authToken.first()
            if (token != null) {
                val response = apiService.createConversation(
                    "${Constants.AUTH_HEADER_PREFIX}$token",
                    CreateConversationRequest(title, isPublic)
                )
                if (response.isSuccessful && response.body()?.success == true) {
                    val conversation = response.body()?.data
                    if (conversation != null) {
                        emit(Resource.Success(conversation))
                    } else {
                        emit(Resource.Error("Failed to create conversation"))
                    }
                } else {
                    emit(Resource.Error(response.body()?.message ?: "Failed to create conversation"))
                }
            } else {
                emit(Resource.Error("Not authenticated"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    suspend fun updateConversation(
        conversationId: String,
        title: String? = null,
        isPublic: Boolean? = null
    ): Flow<Resource<Conversation>> = flow {
        emit(Resource.Loading())
        try {
            val token = preferencesManager.authToken.first()
            if (token != null) {
                val response = apiService.updateConversation(
                    "${Constants.AUTH_HEADER_PREFIX}$token",
                    conversationId,
                    UpdateConversationRequest(title, isPublic)
                )
                if (response.isSuccessful && response.body()?.success == true) {
                    val conversation = response.body()?.data
                    if (conversation != null) {
                        emit(Resource.Success(conversation))
                    } else {
                        emit(Resource.Error("Failed to update conversation"))
                    }
                } else {
                    emit(Resource.Error(response.body()?.message ?: "Failed to update conversation"))
                }
            } else {
                emit(Resource.Error("Not authenticated"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    suspend fun deleteConversation(conversationId: String): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        try {
            val token = preferencesManager.authToken.first()
            if (token != null) {
                val response = apiService.deleteConversation("${Constants.AUTH_HEADER_PREFIX}$token", conversationId)
                if (response.isSuccessful && response.body()?.success == true) {
                    emit(Resource.Success("Conversation deleted successfully"))
                } else {
                    emit(Resource.Error(response.body()?.message ?: "Failed to delete conversation"))
                }
            } else {
                emit(Resource.Error("Not authenticated"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    // ==================== Message Management ====================

    suspend fun getMessages(
        conversationId: String,
        limit: Int? = null,
        offset: Int? = null
    ): Flow<Resource<List<Message>>> = flow {
        emit(Resource.Loading())
        try {
            val token = preferencesManager.authToken.first()
            if (token != null) {
                val response = apiService.getMessages("${Constants.AUTH_HEADER_PREFIX}$token", conversationId, limit, offset)
                if (response.isSuccessful && response.body()?.success == true) {
                    val messages = response.body()?.data ?: emptyList()
                    emit(Resource.Success(messages))
                } else {
                    emit(Resource.Error(response.body()?.message ?: "Failed to load messages"))
                }
            } else {
                emit(Resource.Error("Not authenticated"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    suspend fun sendMessage(
        conversationId: String,
        content: String,
        role: MessageRole = MessageRole.USER
    ): Flow<Resource<Message>> = flow {
        emit(Resource.Loading())
        try {
            val token = preferencesManager.authToken.first()
            if (token != null) {
                val response = apiService.sendMessage(
                    "${Constants.AUTH_HEADER_PREFIX}$token",
                    conversationId,
                    SendMessageRequest(content, conversationId, role)
                )
                if (response.isSuccessful && response.body()?.success == true) {
                    val message = response.body()?.data
                    if (message != null) {
                        emit(Resource.Success(message))
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

    suspend fun deleteMessage(messageId: String): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        try {
            val token = preferencesManager.authToken.first()
            if (token != null) {
                val response = apiService.deleteMessage("${Constants.AUTH_HEADER_PREFIX}$token", messageId)
                if (response.isSuccessful && response.body()?.success == true) {
                    emit(Resource.Success("Message deleted successfully"))
                } else {
                    emit(Resource.Error(response.body()?.message ?: "Failed to delete message"))
                }
            } else {
                emit(Resource.Error("Not authenticated"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    // ==================== Sharing Management ====================

    suspend fun getConversationShares(conversationId: String): Flow<Resource<List<SharedConversation>>> = flow {
        emit(Resource.Loading())
        try {
            val token = preferencesManager.authToken.first()
            if (token != null) {
                val response = apiService.getConversationShares("${Constants.AUTH_HEADER_PREFIX}$token", conversationId)
                if (response.isSuccessful && response.body()?.success == true) {
                    val shares = response.body()?.data ?: emptyList()
                    emit(Resource.Success(shares))
                } else {
                    emit(Resource.Error(response.body()?.message ?: "Failed to load shares"))
                }
            } else {
                emit(Resource.Error("Not authenticated"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    suspend fun shareConversation(
        conversationId: String,
        sharedWithId: String,
        permission: Permission = Permission.VIEW
    ): Flow<Resource<SharedConversation>> = flow {
        emit(Resource.Loading())
        try {
            val token = preferencesManager.authToken.first()
            if (token != null) {
                val response = apiService.shareConversation(
                    "${Constants.AUTH_HEADER_PREFIX}$token",
                    ShareConversationRequest(conversationId, sharedWithId, permission)
                )
                if (response.isSuccessful && response.body()?.success == true) {
                    val share = response.body()?.data
                    if (share != null) {
                        emit(Resource.Success(share))
                    } else {
                        emit(Resource.Error("Failed to share conversation"))
                    }
                } else {
                    emit(Resource.Error(response.body()?.message ?: "Failed to share conversation"))
                }
            } else {
                emit(Resource.Error("Not authenticated"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    suspend fun updateSharePermission(
        shareId: String,
        permission: Permission
    ): Flow<Resource<SharedConversation>> = flow {
        emit(Resource.Loading())
        try {
            val token = preferencesManager.authToken.first()
            if (token != null) {
                val response = apiService.updateSharePermission(
                    "${Constants.AUTH_HEADER_PREFIX}$token",
                    shareId,
                    mapOf("permission" to permission)
                )
                if (response.isSuccessful && response.body()?.success == true) {
                    val share = response.body()?.data
                    if (share != null) {
                        emit(Resource.Success(share))
                    } else {
                        emit(Resource.Error("Failed to update permission"))
                    }
                } else {
                    emit(Resource.Error(response.body()?.message ?: "Failed to update permission"))
                }
            } else {
                emit(Resource.Error("Not authenticated"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    suspend fun removeShare(shareId: String): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        try {
            val token = preferencesManager.authToken.first()
            if (token != null) {
                val response = apiService.removeShare("${Constants.AUTH_HEADER_PREFIX}$token", shareId)
                if (response.isSuccessful && response.body()?.success == true) {
                    emit(Resource.Success("Share removed successfully"))
                } else {
                    emit(Resource.Error(response.body()?.message ?: "Failed to remove share"))
                }
            } else {
                emit(Resource.Error("Not authenticated"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    suspend fun getSharedWithMeConversations(): Flow<Resource<List<Conversation>>> = flow {
        emit(Resource.Loading())
        try {
            val token = preferencesManager.authToken.first()
            if (token != null) {
                val response = apiService.getSharedWithMeConversations("${Constants.AUTH_HEADER_PREFIX}$token")
                if (response.isSuccessful && response.body()?.success == true) {
                    val conversations = response.body()?.data ?: emptyList()
                    emit(Resource.Success(conversations))
                } else {
                    emit(Resource.Error(response.body()?.message ?: "Failed to load shared conversations"))
                }
            } else {
                emit(Resource.Error("Not authenticated"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        }
    }
}

