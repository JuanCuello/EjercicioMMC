package com.todoware.ejerciciomeli


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import okhttp3.mockwebserver.MockResponse
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.junit.Test


/**
 * Real device or emulator that can reach host network ir required to connect to the MockWebServer
 * ip address, to perform the end to end connection
 */
@LargeTest
class SearchResultsScreen : SearchMockBaseTest() {

    // Check empty screen on main view
    @Test
    fun search() {
        onView(
            allOf(
                withText(R.string.msg_welcome_search),
                isDisplayed()
            )
        )

        val textInputEditText = onView(
            allOf(
                withId(R.id.results_edit_text),
                isDisplayed()
            )
        )
        textInputEditText.perform(click())
    }

    // Check search box on this view
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


    // check and start main view, type a search and check the items
    @Test
    fun searchBox_clear_text_after_a_search() {
        //enqueue the ok "got" search response
        server!!.enqueue(MockResponse().setBody(responseOK))


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

        // debounce text
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

        // Edittext Debounce.
        Thread.sleep(600)

        val textView = onView(
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
        textView.check(matches(withText("Funko Pop Jon Snow #49 Got Game Of Thrones Jugueterialeon")))

        val checkableImageButton = onView(
            allOf(
                withId(R.id.text_input_end_icon), withContentDescription("Clear text"),
                childAtPosition(
                    childAtPosition(
                        withClassName(Matchers.`is`("android.widget.FrameLayout")),
                        2
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        checkableImageButton.perform(click())
    }


    @Test
    fun no_result_query() {
        //enqueue the ok "got" search response
        server!!.enqueue(MockResponse().setBody(responseEmpty))

        val textInputEditText = onView(
            allOf(
                withId(R.id.results_edit_text),
                isDisplayed()
            )
        )
        textInputEditText.perform(click())

        val textInputEditText1 = onView(
            allOf(
                withId(R.id.results_edit_text),
                isDisplayed()
            )
        )
        textInputEditText1.perform(replaceText("got"))

        val textInputEditText2 = onView(
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
        textInputEditText2.perform(closeSoftKeyboard())

        // EditText debouncer
        Thread.sleep(600)

        val textView = onView(
            allOf(
                withText(R.string.empty_search),
                isDisplayed()
            )
        )
        textView.check(matches(isDisplayed()))
    }


    @Test
    fun error_query() {
        //enqueue the ok "got" search response
        server!!.enqueue(MockResponse().setBody(responseFail).setResponseCode(404))

        val textInputEditText = onView(
            allOf(
                withId(R.id.results_edit_text),
                isDisplayed()
            )
        )
        textInputEditText.perform(click())

        val textInputEditText1 = onView(
            allOf(
                withId(R.id.results_edit_text),
                isDisplayed()
            )
        )
        textInputEditText1.perform(replaceText("got"))

        val textInputEditText2 = onView(
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
        textInputEditText2.perform(closeSoftKeyboard())

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(600)

        val textView = onView(
            withText(R.string.network_error)
        )
        textView.check(matches(isDisplayed()))
    }
}
