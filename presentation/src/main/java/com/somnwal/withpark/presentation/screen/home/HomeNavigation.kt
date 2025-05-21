package com.somnwal.withpark.presentation.screen.home

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.somnwal.withpark.presentation.common.navigation.MainRoute
import com.somnwal.withpark.presentation.screen.LocalNavController

fun NavGraphBuilder.homeScreen(
    modifier: Modifier = Modifier,
) {
    composable<MainRoute.Home> {
        val navController = LocalNavController.current

        HomeScreen(
            modifier = modifier,
        )
    }
}