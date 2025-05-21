package com.somnwal.withpark.presentation.util

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class SnackbarController(
    private val hostState: SnackbarHostState,
    // SupervisorJob 을 통해 다른 코루틴들이 실패해도 영향없게 처리 (병렬 처리)
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob())
) {

    fun showMessage(
        message: String,
        dismissPrevious: Boolean = true,
        duration: SnackbarDuration = SnackbarDuration.Short
    ) {
        coroutineScope.launch {
            // 이전 스낵바를 닫기
            if(dismissPrevious) {
                hostState.currentSnackbarData?.dismiss()
            }

            // 스낵바 표시
            hostState.showSnackbar(
                message,
                duration = duration
            )
        }
    }
}