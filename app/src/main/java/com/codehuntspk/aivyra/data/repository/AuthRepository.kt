package com.codehuntspk.aivyra.data.repository

import com.codehuntspk.aivyra.data.api.AivyraApiService
import com.codehuntspk.aivyra.data.local.PreferencesManager
import com.codehuntspk.aivyra.data.model.*
import com.codehuntspk.aivyra.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val apiService: AivyraApiService,
    private val preferencesManager: PreferencesManager
) {

    suspend fun login(email: String, password: String): Flow<Resource<AuthData>> = flow {
        emit(Resource.Loading())
        try {
            val response = apiService.login(LoginRequest(email, password))
            if (response.isSuccessful && response.body()?.success == true) {
                val authData = response.body()?.data
                if (authData != null) {
                    // Save token and user data
                    preferencesManager.saveAuthToken(authData.token)
                    preferencesManager.saveUserData(authData.user.id, authData.user.email)
                    emit(Resource.Success(authData))
                } else {
                    emit(Resource.Error("Login failed: No data received"))
                }
            } else {
                val errorMessage = response.body()?.message ?: "Login failed"
                emit(Resource.Error(errorMessage))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred during login"))
        }
    }

    suspend fun signup(
        email: String,
        password: String,
        name: String
    ): Flow<Resource<AuthData>> = flow {
        emit(Resource.Loading())
        try {
            val response = apiService.signup(SignupRequest(email, password, name))
            if (response.isSuccessful && response.body()?.success == true) {
                val authData = response.body()?.data
                if (authData != null) {
                    // Save token and user data
                    preferencesManager.saveAuthToken(authData.token)
                    preferencesManager.saveUserData(authData.user.id, authData.user.email)
                    emit(Resource.Success(authData))
                } else {
                    emit(Resource.Error("Signup failed: No data received"))
                }
            } else {
                val errorMessage = response.body()?.message ?: "Signup failed"
                emit(Resource.Error(errorMessage))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred during signup"))
        }
    }

    suspend fun forgotPassword(email: String): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        try {
            val response = apiService.forgotPassword(ForgotPasswordRequest(email))
            if (response.isSuccessful && response.body()?.success == true) {
                val message = response.body()?.message ?: "OTP sent successfully"
                emit(Resource.Success(message))
            } else {
                val errorMessage = response.body()?.message ?: "Failed to send OTP"
                emit(Resource.Error(errorMessage))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    suspend fun verifyOtp(email: String, otp: String): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        try {
            val response = apiService.verifyOtp(VerifyOtpRequest(email, otp))
            if (response.isSuccessful && response.body()?.success == true) {
                val message = response.body()?.message ?: "OTP verified successfully"
                emit(Resource.Success(message))
            } else {
                val errorMessage = response.body()?.message ?: "Invalid OTP"
                emit(Resource.Error(errorMessage))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    suspend fun resetPassword(
        email: String,
        otp: String,
        newPassword: String
    ): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        try {
            val response = apiService.resetPassword(ResetPasswordRequest(email, otp, newPassword))
            if (response.isSuccessful && response.body()?.success == true) {
                val message = response.body()?.message ?: "Password reset successfully"
                emit(Resource.Success(message))
            } else {
                val errorMessage = response.body()?.message ?: "Failed to reset password"
                emit(Resource.Error(errorMessage))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    suspend fun logout(): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        try {
            val token = preferencesManager.authToken.first()
            if (token != null) {
                apiService.logout("Bearer $token")
            }
            preferencesManager.clearAll()
            emit(Resource.Success("Logged out successfully"))
        } catch (e: Exception) {
            // Clear local data even if API call fails
            preferencesManager.clearAll()
            emit(Resource.Success("Logged out"))
        }
    }

    fun isLoggedIn(): Flow<Boolean> {
        return preferencesManager.isLoggedIn
    }

    suspend fun getCurrentUser(): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        try {
            val token = preferencesManager.authToken.first()
            if (token != null) {
                val response = apiService.getCurrentUser("Bearer $token")
                if (response.isSuccessful && response.body()?.success == true) {
                    val user = response.body()?.data
                    if (user != null) {
                        emit(Resource.Success(user))
                    } else {
                        emit(Resource.Error("Failed to get user data"))
                    }
                } else {
                    emit(Resource.Error(response.body()?.message ?: "Failed to get user"))
                }
            } else {
                emit(Resource.Error("Not authenticated"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        }
    }
}

