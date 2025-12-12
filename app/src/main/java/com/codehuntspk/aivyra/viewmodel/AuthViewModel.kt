package com.codehuntspk.aivyra.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codehuntspk.aivyra.data.model.AuthData
import com.codehuntspk.aivyra.data.repository.AuthRepository
import com.codehuntspk.aivyra.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow<Resource<AuthData>?>(null)
    val loginState: StateFlow<Resource<AuthData>?> = _loginState.asStateFlow()

    private val _signupState = MutableStateFlow<Resource<AuthData>?>(null)
    val signupState: StateFlow<Resource<AuthData>?> = _signupState.asStateFlow()

    private val _forgotPasswordState = MutableStateFlow<Resource<String>?>(null)
    val forgotPasswordState: StateFlow<Resource<String>?> = _forgotPasswordState.asStateFlow()

    private val _verifyOtpState = MutableStateFlow<Resource<String>?>(null)
    val verifyOtpState: StateFlow<Resource<String>?> = _verifyOtpState.asStateFlow()

    private val _logoutState = MutableStateFlow<Resource<String>?>(null)
    val logoutState: StateFlow<Resource<String>?> = _logoutState.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            authRepository.login(email, password).collect { result ->
                _loginState.value = result
            }
        }
    }

    fun signup(email: String, password: String, fullName: String) {
        viewModelScope.launch {
            authRepository.signup(email, password, fullName).collect { result ->
                _signupState.value = result
            }
        }
    }

    fun forgotPassword(email: String) {
        viewModelScope.launch {
            authRepository.forgotPassword(email).collect { result ->
                _forgotPasswordState.value = result
            }
        }
    }

    fun verifyOtp(email: String, otp: String) {
        viewModelScope.launch {
            authRepository.verifyOtp(email, otp).collect { result ->
                _verifyOtpState.value = result
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.logout().collect { result ->
                _logoutState.value = result
            }
        }
    }

    fun resetLoginState() {
        _loginState.value = null
    }

    fun resetSignupState() {
        _signupState.value = null
    }

    fun resetForgotPasswordState() {
        _forgotPasswordState.value = null
    }

    fun resetVerifyOtpState() {
        _verifyOtpState.value = null
    }
}

