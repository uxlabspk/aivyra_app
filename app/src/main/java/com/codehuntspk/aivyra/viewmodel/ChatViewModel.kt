package com.codehuntspk.aivyra.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codehuntspk.aivyra.data.model.ChatMessage
import com.codehuntspk.aivyra.data.model.ChatSession
import com.codehuntspk.aivyra.data.repository.ChatRepository
import com.codehuntspk.aivyra.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel() {

    private val _sendMessageState = MutableStateFlow<Resource<ChatMessage>?>(null)
    val sendMessageState: StateFlow<Resource<ChatMessage>?> = _sendMessageState.asStateFlow()

    private val _chatHistoryState = MutableStateFlow<Resource<List<ChatMessage>>?>(null)
    val chatHistoryState: StateFlow<Resource<List<ChatMessage>>?> = _chatHistoryState.asStateFlow()

    private val _chatSessionsState = MutableStateFlow<Resource<List<ChatSession>>?>(null)
    val chatSessionsState: StateFlow<Resource<List<ChatSession>>?> = _chatSessionsState.asStateFlow()

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()

    private val _currentConversationId = MutableStateFlow<String?>(null)
    val currentConversationId: StateFlow<String?> = _currentConversationId.asStateFlow()

    fun setCurrentConversation(conversationId: String) {
        _currentConversationId.value = conversationId
    }

    fun sendMessage(message: String, conversationId: String, sessionId: String? = null) {
        viewModelScope.launch {
            chatRepository.sendMessage(message, conversationId, sessionId).collect { result ->
                _sendMessageState.value = result
                if (result is Resource.Success) {
                    // Add the new message to the list
                    _messages.value = _messages.value + result.data!!
                }
            }
        }
    }

    fun loadChatHistory(sessionId: String? = null) {
        viewModelScope.launch {
            chatRepository.getChatHistory(sessionId).collect { result ->
                _chatHistoryState.value = result
                if (result is Resource.Success) {
                    _messages.value = result.data ?: emptyList()
                }
            }
        }
    }

    fun loadChatSessions() {
        viewModelScope.launch {
            chatRepository.getChatSessions().collect { result ->
                _chatSessionsState.value = result
            }
        }
    }

    fun deleteSession(sessionId: String) {
        viewModelScope.launch {
            chatRepository.deleteSession(sessionId).collect { result ->
                if (result is Resource.Success) {
                    // Reload sessions after deletion
                    loadChatSessions()
                }
            }
        }
    }

    fun resetSendMessageState() {
        _sendMessageState.value = null
    }

    fun clearMessages() {
        _messages.value = emptyList()
    }
}

