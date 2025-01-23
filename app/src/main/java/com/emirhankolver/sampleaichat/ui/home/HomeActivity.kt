package com.emirhankolver.sampleaichat.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.emirhankolver.sampleaichat.R
import com.emirhankolver.sampleaichat.ui.home.account.HomeAccountScreen
import com.emirhankolver.sampleaichat.ui.home.chat.HomeChatScreen
import com.emirhankolver.sampleaichat.ui.home.history.HomeHistoryScreen
import com.emirhankolver.sampleaichat.ui.theme.SampleAIChatTheme

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SampleAIChatTheme {
                HomeScreen()
            }
        }
    }

    // Sample Screens
    @Composable
    fun HomeScreen() {
        val navController = rememberNavController()

        Scaffold(
            bottomBar = {
                BottomNavigationBar(navController = navController)
            }
        ) { innerPadding ->
            NavHostContainer(navController = navController, innerPadding = innerPadding)
        }
    }

    @Composable
    fun BottomNavigationBar(navController: NavHostController) {
        val items = listOf(
            NavigationItem.Home,
            NavigationItem.Profile,
            NavigationItem.Settings
        )
        NavigationBar {
            val currentRoute =
                navController.currentBackStackEntryAsState().value?.destination?.route
            items.forEach { item ->
                val isSelected = currentRoute == item.route
                val iconResId = if (isSelected) item.selectedIconResId else item.iconResId

                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(iconResId),
                            contentDescription = item.label,
                        )
                    },
                    label = { Text(item.label) },
                    selected = isSelected,
                    onClick = {
                        if (!isSelected) {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
    }

    @Composable
    fun NavHostContainer(navController: NavHostController, innerPadding: PaddingValues) {
        NavHost(
            navController = navController,
            startDestination = NavigationItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavigationItem.Home.route) { HomeChatScreen() }
            composable(NavigationItem.Profile.route) { HomeAccountScreen() }
            composable(NavigationItem.Settings.route) { HomeHistoryScreen() }
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

        data object Profile : NavigationItem(
            route = "history",
            label = "History",
            selectedIconResId = R.drawable.ic_history,
            iconResId = R.drawable.ic_history_outlined,
        )

        data object Settings : NavigationItem(
            route = "account",
            label = "Account",
            selectedIconResId = R.drawable.ic_person,
            iconResId = R.drawable.ic_person_outlined,
        )
    }
}