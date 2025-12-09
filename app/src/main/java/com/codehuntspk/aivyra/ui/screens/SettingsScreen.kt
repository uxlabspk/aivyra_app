package com.codehuntspk.aivyra.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.outlined.ExitToApp

import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codehuntspk.aivyra.ui.theme.Purple600
import com.codehuntspk.aivyra.ui.theme.Slate800



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onLogout: () -> Unit = {}
) {
    var darkModeEnabled by remember { mutableStateOf(true) }
    var notificationsEnabled by remember { mutableStateOf(true) }
    var soundEnabled by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        // Header
        Surface(
            color = Slate800,
            tonalElevation = 4.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Settings",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        // Settings List
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Preferences Section
            item {
                SettingSectionHeader("Preferences")
            }

            item {
                SettingCard(
                    icon = Icons.Outlined.Notifications,
                    title = "Dark Mode",
                    subtitle = "Always use dark theme",
                    hasSwitch = true,
                    switchState = darkModeEnabled,
                    onSwitchChange = { darkModeEnabled = it }
                )
            }

            item {
                SettingCard(
                    icon = Icons.Outlined.Notifications,
                    title = "Notifications",
                    subtitle = "Enable push notifications",
                    hasSwitch = true,
                    switchState = notificationsEnabled,
                    onSwitchChange = { notificationsEnabled = it }
                )
            }

            item {
                SettingCard(
                    icon = Icons.Outlined.Notifications,
                    title = "Sound",
                    subtitle = "Enable sound effects",
                    hasSwitch = true,
                    switchState = soundEnabled,
                    onSwitchChange = { soundEnabled = it }
                )
            }

            // Account Section
            item {
                Spacer(modifier = Modifier.height(8.dp))
                SettingSectionHeader("Account")
            }

            item {
                SettingCard(
                    icon = Icons.Outlined.Person,
                    title = "Language",
                    subtitle = "English",
                    onClick = { /* TODO: Open language selection */ }
                )
            }

            item {
                SettingCard(
                    icon = Icons.Outlined.Lock,
                    title = "Privacy & Security",
                    subtitle = "Manage your privacy settings",
                    onClick = { /* TODO: Open privacy settings */ }
                )
            }

            item {
                SettingCard(
                    icon = Icons.Outlined.Face,
                    title = "Data & Storage",
                    subtitle = "Manage app data and cache",
                    onClick = { /* TODO: Open data settings */ }
                )
            }

            // Support Section
            item {
                Spacer(modifier = Modifier.height(8.dp))
                SettingSectionHeader("Support")
            }

            item {
                SettingCard(
                    icon = Icons.Outlined.Search,
                    title = "Help & Support",
                    subtitle = "Get help and contact support",
                    onClick = { /* TODO: Open help */ }
                )
            }

            item {
                SettingCard(
                    icon = Icons.Outlined.Info,
                    title = "About",
                    subtitle = "App version 1.0.0",
                    onClick = { /* TODO: Open about */ }
                )
            }

            item {
                SettingCard(
                    icon = Icons.Outlined.ArrowDropDown,
                    title = "Terms & Conditions",
                    subtitle = "Read our terms of service",
                    onClick = { /* TODO: Open terms */ }
                )
            }

            // Logout
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                Button(
                    onClick = onLogout,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red.copy(alpha = 0.2f),
                        contentColor = Color.Red
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ExitToApp,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Logout",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}


@Composable
fun SettingSectionHeader(title: String) {
    Text(
        text = title,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.White.copy(alpha = 0.6f),
        modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
    )
}


@Composable
fun SettingCard(
    icon: ImageVector,
    title: String,
    subtitle: String? = null,
    hasSwitch: Boolean = false,
    switchState: Boolean = false,
    onSwitchChange: (Boolean) -> Unit = {},
    onClick: () -> Unit = {}
) {
    Surface(
        color = Color.White.copy(alpha = 0.1f),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (!hasSwitch) Modifier.clickable(onClick = onClick)
                else Modifier
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon
            Surface(
                color = Purple600.copy(alpha = 0.2f),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.size(40.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = Purple600,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            // Content
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
                subtitle?.let {
                    Text(
                        text = it,
                        fontSize = 13.sp,
                        color = Color.White.copy(alpha = 0.6f)
                    )
                }
            }

            // Action
            if (hasSwitch) {
                Switch(
                    checked = switchState,
                    onCheckedChange = onSwitchChange,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = Purple600,
                        uncheckedThumbColor = Color.White.copy(alpha = 0.6f),
                        uncheckedTrackColor = Color.White.copy(alpha = 0.2f)
                    )
                )
            } else {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Open",
                    tint = Color.White.copy(alpha = 0.5f)
                )
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 400, heightDp = 700)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}

