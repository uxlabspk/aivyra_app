package com.codehuntspk.aivyra.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codehuntspk.aivyra.ui.theme.Purple600
import com.codehuntspk.aivyra.ui.theme.Purple900
import com.codehuntspk.aivyra.ui.theme.Slate900


sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    object Chat : BottomNavItem("chat", Icons.Default.Email, "Chat")
    object History : BottomNavItem("history", Icons.AutoMirrored.Filled.List, "History")
    object Settings : BottomNavItem("settings", Icons.Default.Settings, "Settings")
    object Profile : BottomNavItem("profile", Icons.Default.Person, "Profile")
}


@Composable
fun MainScreen(
    onLogout: () -> Unit = {}
) {
    var selectedTab by remember { mutableStateOf(0) }

    val navItems = listOf(
        BottomNavItem.Chat,
        BottomNavItem.History,
        BottomNavItem.Settings,
        BottomNavItem.Profile
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Slate900,
                contentColor = Color.White,
                tonalElevation = 0.dp
            ) {
                navItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label
                            )
                        },
                        label = { Text(item.label) },
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Purple600,
                            selectedTextColor = Purple600,
                            unselectedIconColor = Color.White.copy(alpha = 0.6f),
                            unselectedTextColor = Color.White.copy(alpha = 0.6f),
                            indicatorColor = Purple600.copy(alpha = 0.2f)
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Slate900,
                            Purple900.copy(alpha = 0.6f),
                            Slate900
                        )
                    )
                )
        ) {
            when (selectedTab) {
                0 -> ChatScreen()
                1 -> HistoryScreen()
                2 -> SettingsScreen(onLogout = onLogout)
                3 -> ProfileScreen()
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 400, heightDp = 700)
@Composable
fun MainScreenPreview() {
    MainScreen()
}

