package com.emirhankolver.sampleaichat.ui.home

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.emirhankolver.sampleaichat.ui.home.components.BottomNavigationBar
import com.emirhankolver.sampleaichat.ui.home.components.NavHostContainer

@Composable
fun HomeScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        NavHostContainer(
            navController = navController,
            innerPadding = innerPadding,
        )
    }
}