package com.todoware.ejerciciomeli

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import okhttp3.mockwebserver.MockResponse
import org.hamcrest.Matchers.allOf
import org.junit.Test

/**
 * Real device or emulator that can reach host network ir required to connect to the MockWebServer
 * ip address, to perform the end to end connection
 */

class NavigationBetweenScreens : SearchMockBaseTest() {

    @Test
    fun navigationBetweenScreens() {
        //enqueue the ok "got" search response
        server!!.enqueue(MockResponse().setBody(responseOK))
        val textView = onView(
            allOf(
                withText(R.string.title_dashboard),
                childAtPosition(
                    allOf(
                        withId(R.id.action_bar),
                        childAtPosition(
                            withId(R.id.action_bar_container),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView.check(matches(isDisplayed()))

        val button = onView(
            allOf(
                withId(R.id.result_filter_button),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    2
                ),
                isDisplayed()

            )
        )

        // Edittext debounce
        Thread.sleep(600)

        val textInputEditText3 = onView(
            allOf(
                withId(R.id.results_edit_text),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.results_text_container),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText3.perform(replaceText("got"))

        val textInputEditText4 = onView(
            allOf(
                withId(R.id.results_edit_text), withText("got"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.results_text_container),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText4.perform(closeSoftKeyboard())

        // Debounce of edit text
        Thread.sleep(600)

        val button2 = onView(
            allOf(
                withId(R.id.result_filter_button),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        button2.check(matches(isDisplayed()))

        // Debounce of edit text
        Thread.sleep(600)

        val appCompatButton = onView(
            allOf(
                withId(R.id.result_filter_button),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatButton.perform(click())

        // Debounce of edit text
        Thread.sleep(1000)

        val linearLayout = onView(
            allOf(
                withId(R.id.recycle_item_row),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )

    }

}
