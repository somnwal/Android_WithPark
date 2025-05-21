package com.somnwal.withpark.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ToastSnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
) {
    SnackbarHost(
        // 호스트 스테이트에 의해 호출됨
        hostState = hostState,
        modifier = modifier
    ) { data ->
        // 스낵바 데이터 반환

        Card(
            // 모서리 둥글게
            shape = RoundedCornerShape(4.dp),
            // 카드 색상 지정
            colors = CardDefaults.cardColors(
                // 카드 배경 색
                containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = .8f),
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 아이콘이 있으면
                leadingIcon?.let {
                    // 입력받은 대로 아이콘 그리기
                    leadingIcon()
                    // 아이콘 그리고 나서 끝에 약간의 마진 주기
                    Spacer(modifier = Modifier.padding(end = 8.dp))
                }

                Text(
                    modifier = Modifier
                        .padding(vertical = 12.dp),
                    text = data.visuals.message,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}