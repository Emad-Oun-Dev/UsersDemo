
package com.example.tatweerdemo.presentation.features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tatweerdemo.utils.UsersScreen

/**
 * Created by Emad Mohamed Oun
 * Speedi
 * emad.3oon@gmail.com
 */

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = UsersScreen
    ) {
        composable(
            route = UsersScreen
        ) {
            UsersScreenDestination()
        }
    }
}


