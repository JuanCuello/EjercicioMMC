package com.todoware.ejerciciomeli.utils

import android.os.Handler
import com.todoware.ejerciciomeli.utils.UiUtils.editTextDebounce
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class UiUtilsTest {

    val spyHandler = mock(Handler::class.java)

    @Mock
    lateinit var callbackPosted: (String) -> Unit

    // Happy path, text.length > 1 and not the last search
    @Test
    fun editTextDebounce_callback_called() {

        val searchTerm = "ryzen"
        val oldSearch = "pixel 2"

        // forcing runnable to be called imediatly
        `when`(
            spyHandler.postDelayed(
                any(Runnable::class.java),
                anyLong()
            )
        ).thenAnswer { invocation ->
            invocation.getArgument(0, Runnable::class.java).run()
            null
        }

        editTextDebounce(searchTerm, oldSearch, callbackPosted, spyHandler)
        Thread.sleep(100)
        verify(callbackPosted).invoke(searchTerm)

    }

    // Happy path, text.length > 1 and not the last search
    @Test
    fun editTextDebounce_new_seat() {

        val searchTerm = "ryzen"
        val oldSearch = "pixel 2"

        // forcing runnable to be called imediatly
        `when`(
            spyHandler.postDelayed(
                any(Runnable::class.java),
                anyLong()
            )
        ).thenAnswer { invocation ->
            invocation.getArgument(0, Runnable::class.java).run()
            null
        }

        editTextDebounce(searchTerm, oldSearch, callbackPosted, spyHandler)
        Thread.sleep(100)
        verify(callbackPosted).invoke(searchTerm)

    }


    // Empty search term
    @Test
    fun editTextDebounce_NoText() {
        val searchTerm = ""
        val oldSearch = "pixel 2"


        editTextDebounce(searchTerm, oldSearch, callbackPosted, spyHandler)
        Thread.sleep(100)
        verifyNoMoreInteractions(callbackPosted)

    }

    // Length equal to 1 search term should be skipped
    @Test
    fun editTextDebounce_SameOldSearch() {
        val searchTerm = "2"
        val oldSearch = "pixel 2"

        editTextDebounce(searchTerm, oldSearch, callbackPosted, spyHandler)
        Thread.sleep(100)
        verifyNoMoreInteractions(callbackPosted)

    }

    // Search term should not be different to the last one
    @Test
    fun editTextDebounce_LessThat2Chars() {
        val searchTerm = "TV led"
        val oldSearch = "TV led"

        editTextDebounce(searchTerm, oldSearch, callbackPosted, spyHandler)
        Thread.sleep(100)
        verifyNoMoreInteractions(callbackPosted)

    }
}