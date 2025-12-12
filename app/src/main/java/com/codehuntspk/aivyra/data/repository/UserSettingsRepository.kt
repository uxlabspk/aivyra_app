package com.codehuntspk.aivyra.data.repository

import com.codehuntspk.aivyra.data.api.AivyraApiService
import com.codehuntspk.aivyra.data.local.PreferencesManager
import com.codehuntspk.aivyra.data.model.UpdateUserSettingsRequest
import com.codehuntspk.aivyra.data.model.UserSettings
import com.codehuntspk.aivyra.utils.Constants
import com.codehuntspk.aivyra.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSettingsRepository @Inject constructor(
    private val apiService: AivyraApiService,
    private val preferencesManager: PreferencesManager
) {

    suspend fun getUserSettings(): Flow<Resource<UserSettings>> = flow {
        emit(Resource.Loading())
        try {
            val token = preferencesManager.authToken.first()
            if (token != null) {
                val response = apiService.getUserSettings("${Constants.AUTH_HEADER_PREFIX}$token")
                if (response.isSuccessful && response.body()?.success == true) {
                    val settings = response.body()?.data
                    if (settings != null) {
                        emit(Resource.Success(settings))
                    } else {
                        emit(Resource.Error("Failed to load settings"))
                    }
                } else {
                    emit(Resource.Error(response.body()?.message ?: "Failed to load settings"))
                }
            } else {
                emit(Resource.Error("Not authenticated"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    suspend fun updateUserSettings(request: UpdateUserSettingsRequest): Flow<Resource<UserSettings>> = flow {
        emit(Resource.Loading())
        try {
            val token = preferencesManager.authToken.first()
            if (token != null) {
                val response = apiService.updateUserSettings(
                    "${Constants.AUTH_HEADER_PREFIX}$token",
                    request
                )
                if (response.isSuccessful && response.body()?.success == true) {
                    val settings = response.body()?.data
                    if (settings != null) {
                        emit(Resource.Success(settings))
                    } else {
                        emit(Resource.Error("Failed to update settings"))
                    }
                } else {
                    emit(Resource.Error(response.body()?.message ?: "Failed to update settings"))
                }
            } else {
                emit(Resource.Error("Not authenticated"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    suspend fun resetUserSettings(): Flow<Resource<UserSettings>> = flow {
        emit(Resource.Loading())
        try {
            val token = preferencesManager.authToken.first()
            if (token != null) {
                val response = apiService.resetUserSettings("${Constants.AUTH_HEADER_PREFIX}$token")
                if (response.isSuccessful && response.body()?.success == true) {
                    val settings = response.body()?.data
                    if (settings != null) {
                        emit(Resource.Success(settings))
                    } else {
                        emit(Resource.Error("Failed to reset settings"))
                    }
                } else {
                    emit(Resource.Error(response.body()?.message ?: "Failed to reset settings"))
                }
            } else {
                emit(Resource.Error("Not authenticated"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    // Convenience methods for updating individual settings

    suspend fun updateTheme(theme: String): Flow<Resource<UserSettings>> {
        return updateUserSettings(UpdateUserSettingsRequest(theme = theme))
    }

    suspend fun updateFontSize(fontSize: Int): Flow<Resource<UserSettings>> {
        return updateUserSettings(UpdateUserSettingsRequest(fontSize = fontSize))
    }

    suspend fun updateNotificationSettings(
        emailNotifications: Boolean? = null,
        pushNotifications: Boolean? = null,
        soundEnabled: Boolean? = null,
        desktopNotifications: Boolean? = null
    ): Flow<Resource<UserSettings>> {
        return updateUserSettings(
            UpdateUserSettingsRequest(
                emailNotifications = emailNotifications,
                pushNotifications = pushNotifications,
                soundEnabled = soundEnabled,
                desktopNotifications = desktopNotifications
            )
        )
    }

    suspend fun updateAISettings(
        aiModel: String? = null,
        temperature: Float? = null,
        maxTokens: Int? = null
    ): Flow<Resource<UserSettings>> {
        return updateUserSettings(
            UpdateUserSettingsRequest(
                aiModel = aiModel,
                temperature = temperature,
                maxTokens = maxTokens
            )
        )
    }

    suspend fun updatePrivacySettings(
        showOnlineStatus: Boolean? = null,
        allowAnalytics: Boolean? = null,
        shareUsageData: Boolean? = null
    ): Flow<Resource<UserSettings>> {
        return updateUserSettings(
            UpdateUserSettingsRequest(
                showOnlineStatus = showOnlineStatus,
                allowAnalytics = allowAnalytics,
                shareUsageData = shareUsageData
            )
        )
    }
}

