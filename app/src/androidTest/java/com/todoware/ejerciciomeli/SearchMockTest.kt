package com.todoware.ejerciciomeli


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.todoware.ejerciciomeli.service.MercadoLibreService
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class SearchMockTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)
    var server: MockWebServer? = null

    @Before
    fun initialize() {

        server = MockWebServer().also {
            it.start()
            val baseUrl = it.url("/")
            MercadoLibreService.overrideBaseUrl = baseUrl.toString()
            it.dispatcher = dispatcherForSearch()
        }

    }

    @Test
    fun searchMockTest() {

        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.navigation_dashboard), withContentDescription("Dashboard"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_view),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )

        bottomNavigationItemView.perform(click())

        val bottomNavigationItemView2 = onView(
            allOf(
                withId(R.id.navigation_dashboard), withContentDescription("Dashboard"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_view),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )


    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }


    private fun dispatcherForSearch(): Dispatcher {
        return object : Dispatcher() {
            @Throws(InterruptedException::class)
            override fun dispatch(request: RecordedRequest): MockResponse {
                var path = request.path?.split("?")?.get(0)
                when (path) {
                    "/sites/MLA/search" -> return MockResponse().setResponseCode(200)
                        .setBody(
                            """{"site_id":"MLA","query":"macbook","paging":{"total":1685,"offset":0,"limit":50,"primary_results":1000},"results":[{"id":"MLA868133474","site_id":"MLA","title":"Macbook Air 13.3 Intel Core I3 256gb Ssd, 8gb Modelo 2020","seller":{"id":76356569,"permalink":"http://perfil.mercadolibre.com.ar/TRICUBO","registration_date":"2004-10-18T22:29:29.000-04:00","car_dealer":false,"real_estate_agency":false,"tags":["normal","user_info_verified","credits_priority_4","credits_profile","eshop","messages_as_seller","messages_as_buyer"],"eshop":{"nick_name":"TRICUBO","eshop_rubro":null,"eshop_id":6809,"eshop_locations":[],"site_id":"MLA","eshop_logo_url":"http://resources.mlstatic.com/eshops/76356569.jpg","eshop_status_id":2,"seller":76356569,"eshop_experience":0},"seller_reputation":{"transactions":{"total":28893,"canceled":1184,"period":"historic","ratings":{"negative":0.01,"positive":0.98,"neutral":0.01},"completed":27709},"power_seller_status":"platinum","metrics":{"claims":{"rate":0.0005,"value":3,"period":"3 months"},"delayed_handling_time":{"rate":0,"value":0,"period":"3 months"},"sales":{"period":"3 months","completed":5157},"cancellations":{"rate":0,"value":0,"period":"3 months"}},"level_id":"5_green"}},"price":179000,"currency_id":"ARS","available_quantity":1,"sold_quantity":0,"buying_mode":"buy_it_now","listing_type_id":"gold_special","stop_time":"2040-07-09T04:00:00.000Z","condition":"new","permalink":"https://articulo.mercadolibre.com.ar/MLA-868133474-macbook-air-133-intel-core-i3-256gb-ssd-8gb-modelo-2020-_JM","thumbnail":"http://mla-s1-p.mlstatic.com/636185-MLA42670308493_072020-I.jpg","accepts_mercadopago":true,"installments":{"quantity":12,"amount":25383.69,"rate":70.17,"currency_id":"ARS"},"address":{"state_id":"AR-C","state_name":"Capital Federal","city_id":"TUxBQlNBTjgzMjRa","city_name":"San Rafael"},"shipping":{"free_shipping":true,"mode":"me2","tags":["fulfillment","self_service_out","mandatory_free_shipping"],"logistic_type":"fulfillment","store_pick_up":false},"seller_address":{"id":"","comment":"","address_line":"","zip_code":"","country":{"id":"AR","name":"Argentina"},"state":{"id":"AR-C","name":"Capital Federal"},"city":{"id":"TUxBQlNBTjgzMjRa","name":"San Rafael"},"latitude":"","longitude":""},"attributes":[{"value_id":"9344","value_name":"Apple","value_struct":null,"values":[{"id":"9344","name":"Apple","struct":null,"source":6808261514773724}],"attribute_group_id":"OTHERS","source":6808261514773724,"id":"BRAND","attribute_group_name":"Otros","name":"Marca"},{"value_name":"Nuevo","values":[{"name":"Nuevo","struct":null,"source":6808261514773724,"id":"2230284"}],"source":6808261514773724,"value_id":"2230284","name":"Condición del ítem","value_struct":null,"attribute_group_id":"OTHERS","attribute_group_name":"Otros","id":"ITEM_CONDITION"},{"value_id":"95565","value_name":"MacBook Air","values":[{"id":"95565","name":"MacBook Air","struct":null,"source":6808261514773724}],"id":"LINE","name":"Línea","attribute_group_name":"Otros","source":6808261514773724,"value_struct":null,"attribute_group_id":"OTHERS"},{"name":"Modelo","value_id":"8773476","values":[{"id":"8773476","name":"A2179","struct":null,"source":3376461333454861}],"attribute_group_id":"OTHERS","attribute_group_name":"Otros","source":3376461333454861,"id":"MODEL","value_name":"A2179","value_struct":null},{"name":"Marca del procesador","value_struct":null,"values":[{"id":"7855833","name":"Intel","struct":null,"source":6808261514773724}],"attribute_group_name":"Otros","source":6808261514773724,"id":"PROCESSOR_BRAND","value_id":"7855833","value_name":"Intel","attribute_group_id":"OTHERS"},{"id":"PROCESSOR_LINE","name":"Línea del procesador","attribute_group_name":"Otros","source":6808261514773724,"value_id":"7769179","value_name":"Core i3","value_struct":null,"values":[{"id":"7769179","name":"Core i3","struct":null,"source":6808261514773724}],"attribute_group_id":"OTHERS"}],"original_price":null,"category_id":"MLA1652","official_store_id":null,"domain_id":"MLA-NOTEBOOKS","catalog_product_id":"MLA15913938","tags":["good_quality_picture","good_quality_thumbnail","immediate_payment","cart_eligible","shipping_guaranteed"]}],"secondary_results":[],"related_results":[],"sort":{"id":"relevance","name":"Más relevantes"},"available_sorts":[{"id":"price_asc","name":"Menor precio"},{"id":"price_desc","name":"Mayor precio"}]}"""
                        )
                }
                return MockResponse().setResponseCode(404)
            }
        }

    }
}
