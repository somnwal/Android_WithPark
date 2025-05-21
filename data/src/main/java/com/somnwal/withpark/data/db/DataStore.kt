package com.somnwal.withpark.data.db

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore

// 데이터 스토어가 저장되는 방식을 명세
internal val Context.dataStore: DataStore<AppSettings> by dataStore(
    "app-settings.json",
    AppSettingsSerializer
)