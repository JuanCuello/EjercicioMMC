package com.todoware.ejerciciomeli


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import okhttp3.mockwebserver.MockResponse
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Test

class DescriptionScreenTests : SearchMockBaseTest() {

    // Safe to use harcoded since its mocket in SearchMockBaseTest

    @Test
    fun assertPortraitDescriptionItems() {
        //enqueue the ok "got" search response
        server!!.enqueue(MockResponse().setBody(responseOK))

        val textInputEditText = onView(
            allOf(
                withId(R.id.results_edit_text),
                isDisplayed()
            )
        )
        textInputEditText.perform(click())

        // EditText debounce
        Thread.sleep(600)

        val textInputEditText1 = onView(
            allOf(
                withId(R.id.results_edit_text),
                isDisplayed()
            )
        )
        textInputEditText1.perform(replaceText("got"))
        textInputEditText1.perform(closeSoftKeyboard())

        // EditText debounce
        Thread.sleep(800)

        val textViewRow = onView(
            allOf(
                withText("Funko Pop Jon Snow #49 Got Game Of Thrones Jugueterialeon"),
                isDisplayed()
            )
        )
        textViewRow.perform(click())
        // Safe to use harcoded since its mocket in SearchMockBaseTest

        val textView = onView(
            allOf(
                withId(R.id.description_title_text),
                withText("Funko Pop Jon Snow #49 Got Game Of Thrones Jugueterialeon"),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Funko Pop Jon Snow #49 Got Game Of Thrones Jugueterialeon")))

        val textView2 = onView(
            allOf(
                withId(R.id.description_title_text),
                withText("Funko Pop Jon Snow #49 Got Game Of Thrones Jugueterialeon"),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Funko Pop Jon Snow #49 Got Game Of Thrones Jugueterialeon")))

        val imageView = onView(
            allOf(
                withId(R.id.item_description_img),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))

        val textView3 = onView(
            allOf(
                withText(R.string.description_price),
                isDisplayed()
            )
        )
        textView3.check(matches(isDisplayed()))

        val textView4 = onView(
            allOf(
                withText(R.string.description_delivery),
                isDisplayed()
            )
        )
        textView4.check(matches(isDisplayed()))

        val textView5 = onView(
            allOf(
                withText(R.string.description_location),
                isDisplayed()
            )
        )
        textView5.check(matches(isDisplayed()))

        val textView6 = onView(
            allOf(
                withId(R.id.item_description_location_text), withText("Almagro,  Capital Federal "),
                isDisplayed()
            )
        )
        textView6.check(matches(withText("Almagro,  Capital Federal ")))

    }
}
