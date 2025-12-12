package com.codehuntspk.aivyra.utils

object Constants {
    // Backend API Base URL
    // NeonDB Database: postgresql://neondb_owner:npg_iyCOI2Ra8Kxm@ep-crimson-fog-a174634p-pooler.ap-southeast-1.aws.neon.tech/neondb
    // TODO: Replace with your actual backend API URL after deploying your backend
    // Example for local development: "http://10.0.2.2:3000/api/" (Android emulator)
    // Example for production: "https://your-api-domain.com/api/"
    const val BASE_URL = "https://your-backend-api.com/api/"

    // DataStore Keys
    const val DATASTORE_NAME = "aivyra_preferences"
    const val KEY_AUTH_TOKEN = "auth_token"
    const val KEY_USER_EMAIL = "user_email"
    const val KEY_USER_ID = "user_id"
    const val KEY_USER_NAME = "user_name"
    const val KEY_USER_ROLE = "user_role"
    const val KEY_USER_AVATAR = "user_avatar"
    const val KEY_IS_LOGGED_IN = "is_logged_in"
    const val KEY_EMAIL_VERIFIED = "email_verified"

    // Shared Preferences Keys (fallback)
    const val PREFS_NAME = "aivyra_prefs"

    // API Constants
    const val AUTH_HEADER_PREFIX = "Bearer "
    const val TIMEOUT_CONNECT = 30L // seconds
    const val TIMEOUT_READ = 30L // seconds
    const val TIMEOUT_WRITE = 30L // seconds

    // Pagination
    const val DEFAULT_PAGE_SIZE = 20
    const val MAX_PAGE_SIZE = 100

    // AI Model Defaults
    const val DEFAULT_AI_MODEL = "gemini-2.5-flash"
    const val DEFAULT_TEMPERATURE = 0.7f
    const val DEFAULT_MAX_TOKENS = 2048
}

