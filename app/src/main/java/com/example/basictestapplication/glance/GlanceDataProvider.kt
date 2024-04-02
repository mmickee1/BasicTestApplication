package com.example.basictestapplication.glance

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class GlanceDataProvider(val context: Context) {

    init {
        CoroutineScope(Dispatchers.IO).launch {
            context.dataStore.edit { preferences ->
                preferences[glance_text_key] = "Sample text"
            }
        }
    }

    val glance_text_key = stringPreferencesKey("glance_text_key")
    suspend fun saveText(text: String) {
        context.dataStore.edit { preferences ->
            preferences[glance_text_key] = text
        }
    }

    val glanceTextFlow: Flow<String> =
        context.dataStore.data.map { preferences ->
            preferences[glance_text_key] ?: "empty"
        }
}
