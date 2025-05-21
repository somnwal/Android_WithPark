package com.somnwal.withpark.presentation.common.navigation

import kotlinx.serialization.Serializable

sealed interface MainRoute {
    @Serializable
    data object Home: MainRoute
}