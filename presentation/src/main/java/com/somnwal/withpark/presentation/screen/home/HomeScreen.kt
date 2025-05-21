package com.somnwal.withpark.presentation.screen.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.somnwal.withpark.presentation.screen.LocalSnackbarController

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val snackbarController = LocalSnackbarController.current
    val navController = rememberNavController()

    // 라우트가 바뀔 때마다 감지하기 위해 asState() 로 불러옴
    val currentBackStack by navController.currentBackStackEntryAsState()

    val isLoggedIn by viewModel.loggedIn.collectAsState()

    LaunchedEffect(Unit) {
        snackbarController.showMessage("홈 화면")
    }

    Scaffold(
        bottomBar = {

        }
    ) { innerPadding ->
        Row(
            modifier = modifier
                .padding(innerPadding),
        ) {
            Text(
                text = isLoggedIn.toString()
            )

        }
    }
}