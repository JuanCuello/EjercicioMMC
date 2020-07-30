package com.todoware.ejerciciomeli


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.todoware.ejerciciomeli.service.MercadoLibreService
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
class SearchResults : SearchMockTest() {


    @Test
    fun searchBox_showed() {
        val editText = onView(
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
        editText.check(matches(isDisplayed()))

        val textInputEditText = onView(
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
        textInputEditText.perform(click())
    }

    @Test
    fun searchBox_clear_text_after_a_search() {
        val editText = onView(
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
        editText.check(matches(isDisplayed()))

        val textInputEditText = onView(
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
        textInputEditText.perform(click())

        val textInputEditText2 = onView(
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
        textInputEditText2.perform(replaceText("got"), closeSoftKeyboard())

        // Added a sleep statement to match the app's execution delay.
        Thread.sleep(400)


        val textView = onView(
            allOf(
                withId(R.id.results_text_description), withText("got"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView.check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.item_result_title),
                withText("Funko Pop Jon Snow #49 Got Game Of Thrones Jugueterialeon"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.recycle_item_row),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )

        val imageButton = onView(
            allOf(
                withId(R.id.text_input_end_icon), withContentDescription("Clear text"),
                isDisplayed()
            )
        )
        imageButton.check(matches(isDisplayed()))
    }

}
