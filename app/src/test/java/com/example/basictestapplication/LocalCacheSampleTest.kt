package com.example.basictestapplication

import android.bluetooth.BluetoothDevice
import io.mockk.impl.annotations.MockK
import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class LocalCacheSampleTest {

    @MockK
    private lateinit var mockBluetoothDevice: BluetoothDevice

    private lateinit var sut: LocalCacheSample

    @Before
    fun setup() {
        sut = LocalCacheSampleImpl(
            // NOTE: if we were to mock anything we could pass those mocks here
            // bluetoothDevice = mockBluetoothDevice
        )
    }

    @Test
    fun testCacheIsEmpty() {
        // given
        val todoList = sut.getTodos()

        // expect
        Assert.assertEquals(0, todoList.size)
    }

    @Test
    fun testCacheHasValueAfterSettingOne() {
        // given
        sut.setTodos(listOf(TodoList("Hello tester!")))

        // when
        val todoList = sut.getTodos()

        // then
        Assert.assertEquals(1, todoList.size)
        Assert.assertEquals("Hello tester!", todoList.first()?.data)
    }
}