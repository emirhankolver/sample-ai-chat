package com.emirhankolver.sampleaichat.ui.home.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.emirhankolver.sampleaichat.R
import com.emirhankolver.sampleaichat.ui.home.account.HomeAccountScreen
import com.emirhankolver.sampleaichat.ui.home.chat.HomeChatScreen
import com.emirhankolver.sampleaichat.ui.home.history.HomeHistoryScreen


@Composable
fun NavHostContainer(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        modifier = Modifier.fillMaxSize().padding(bottom = innerPadding.calculateBottomPadding()),
        navController = navController,
        startDestination = NavigationItem.Home.route,
    ) {
        composable(NavigationItem.Home.route) { HomeChatScreen() }
        composable(NavigationItem.History.route) { HomeHistoryScreen() }
        composable(NavigationItem.Account.route) { HomeAccountScreen() }
    }
}

// Define Navigation Items
sealed class NavigationItem(
    val route: String,
    val label: String,
    val selectedIconResId: Int,
    val iconResId: Int,
) {
    data object Home : NavigationItem(
        route = "chat",
        label = "Chat",
        selectedIconResId = R.drawable.ic_chat,
        iconResId = R.drawable.ic_chat_outlined,
    )

    data object History : NavigationItem(
        route = "history",
        label = "History",
        selectedIconResId = R.drawable.ic_history,
        iconResId = R.drawable.ic_history_outlined,
    )

    data object Account : NavigationItem(
        route = "account",
        label = "Account",
        selectedIconResId = R.drawable.ic_person,
        iconResId = R.drawable.ic_person_outlined,
    )
}