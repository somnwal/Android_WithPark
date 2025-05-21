package com.somnwal.withpark.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.somnwal.withpark.presentation.common.navigation.MainRoute
import com.somnwal.withpark.presentation.component.ToastSnackbarHost
import com.somnwal.withpark.presentation.screen.home.homeScreen
import com.somnwal.withpark.presentation.theme.WithparkTheme
import com.somnwal.withpark.presentation.util.SnackbarController

// 스낵바 컨트롤러를 전역적으로 쓸 수 있게 설정
val LocalSnackbarController = staticCompositionLocalOf {
    SnackbarController(SnackbarHostState())
}

// 네비게이션 컨트롤러를 전역적으로 쓸 수 있게 설정
val LocalNavController = compositionLocalOf<NavHostController> {
    error("네비게이션 컨트롤러가 제공되지 않았습니다.")
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Main(

) {
    val modifier = Modifier.fillMaxSize()
    val scope = rememberCoroutineScope()

    // 토스트 메시지를 위한 스낵바호스트
    val snackbarHostState = remember { SnackbarHostState() }

    // 네비게이션 컨트롤러
    val rootNavController = rememberNavController()

    WithparkTheme {
        Scaffold(
            snackbarHost = {
                ToastSnackbarHost(
                    modifier = Modifier
                        .padding(bottom = 80.dp),
                    hostState = snackbarHostState
                )
            }
        ) {
            /**
             * CompositionLocalProvider 를 통해
             * 하위 컴포넌트 어디서든 LocalSnackbarController 등으로 접근가능하게 만듬
            */
            CompositionLocalProvider(
                LocalSnackbarController provides SnackbarController(
                    snackbarHostState,
                    scope
                ),
                LocalNavController provides rootNavController
            ) {
                MainNavigation(
                    modifier = modifier,
                )
            }
        }
    }
}

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier
) {
    val navController = LocalNavController.current

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = MainRoute.Home
    ) {
        // 홈 화면
        homeScreen()
    }
}