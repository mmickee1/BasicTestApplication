package com.example.basictestapplication

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MainViewModel @Inject constructor(
    private val cache: LocalCacheSampleImpl
) : ViewModel() {


    fun setDataToCache() {
        val data = Random.nextInt()
        cache.setTodos(listOf(TodoList(data.toString())))
    }

    val data = cache.cache

    suspend fun getData() = cache.getTodosFlow()

    fun getData2(): List<TodoList?> {
        return cache.getTodos()
    }
}