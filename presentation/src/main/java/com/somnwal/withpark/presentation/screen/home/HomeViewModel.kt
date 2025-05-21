package com.somnwal.withpark.presentation.screen.home

import androidx.lifecycle.viewModelScope
import com.somnwal.withpark.domain.repository.AuthRepository
import com.somnwal.withpark.presentation.common.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authRepository: AuthRepository
): BaseViewModel() {

    // 로그인 여부
    val loggedIn = authRepository.loggedIn.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        null
    )
}