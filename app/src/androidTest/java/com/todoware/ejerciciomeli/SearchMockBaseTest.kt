package com.todoware.ejerciciomeli


import android.view.View
import android.view.ViewGroup
import androidx.test.rule.ActivityTestRule
import com.todoware.ejerciciomeli.service.MercadoLibreService
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Rule


open class SearchMockBaseTest {
    var responseOK =
        """{"site_id":"MLA","query":"got","paging":{"total":14063,"offset":0,"limit":50,"primary_results":1000},"results":[{"id":"MLA754748288","site_id":"MLA","title":"Funko Pop Jon Snow #49 Got Game Of Thrones Jugueterialeon","seller":{"id":17387880,"permalink":"http://perfil.mercadolibre.com.ar/REGALOSLEON","registration_date":"2012-01-05T10:33:50.000-04:00","car_dealer":false,"real_estate_agency":false,"tags":["normal","user_info_verified","credits_priority_4","credits_profile","eshop","mshops","messages_as_seller","messages_as_buyer"],"eshop":{"nick_name":"REGALOSLEON","eshop_rubro":null,"eshop_id":146991,"eshop_locations":[],"site_id":"MLA","eshop_logo_url":"http://resources.mlstatic.com/eshops/17387880.jpg","eshop_status_id":1,"seller":17387880,"eshop_experience":0},"seller_reputation":{"transactions":{"total":26523,"canceled":761,"period":"historic","ratings":{"negative":0,"positive":1,"neutral":0},"completed":25762},"power_seller_status":"platinum","metrics":{"claims":{"rate":0.0018,"value":7,"period":"3 months"},"delayed_handling_time":{"rate":0.0002,"value":1,"period":"3 months"},"sales":{"period":"3 months","completed":3675},"cancellations":{"rate":0.0023,"value":9,"period":"3 months"}},"level_id":"5_green"}},"price":2899.99,"currency_id":"ARS","available_quantity":1,"sold_quantity":5,"buying_mode":"buy_it_now","listing_type_id":"gold_special","stop_time":"2038-10-18T04:00:00.000Z","condition":"new","permalink":"https://articulo.mercadolibre.com.ar/MLA-754748288-funko-pop-jon-snow-49-got-game-of-thrones-jugueterialeon-_JM","thumbnail":"http://mla-s1-p.mlstatic.com/928700-MLA31022214745_062019-I.jpg",
            |"accepts_mercadopago":true,"installments":{"quantity":12,"amount":411.24,"rate":70.17,"currency_id":"ARS"},"address":{"state_id":"AR-C","state_name":"Capital Federal","city_id":"TUxBQkFMTTMwNTBa","city_name":"Almagro"},"shipping":{"free_shipping":true,"mode":"me2","tags":["self_service_in","mandatory_free_shipping"],"logistic_type":"drop_off","store_pick_up":false},"seller_address":{"id":"","comment":"","address_line":"","zip_code":"","country":{"id":"AR","name":"Argentina"},"state":{"id":"AR-C","name":"Capital Federal"},"city":{"id":"TUxBQkFMTTMwNTBa","name":"Almagro"},"latitude":"","longitude":""},"attributes":[{"source":1572,"id":"ITEM_CONDITION","value_id":"2230284","value_name":"Nuevo","value_struct":null,"attribute_group_id":"OTHERS","name":"Condición del ítem","values":[{"struct":null,"source":1572,"id":"2230284","name":"Nuevo"}],"attribute_group_name":"Otros"},{"attribute_group_name":"Otros","source":4060709329251625,"value_name":"FUNKO POP","value_struct":null,"values":[{"id":null,"name":"FUNKO POP","struct":null,"source":4060709329251625}],"attribute_group_id":"OTHERS","id":"LINE","name":"Línea","value_id":null}],"original_price":null,"category_id":"MLA3422","official_store_id":null,"domain_id":"MLA-ACTION_FIGURES","catalog_product_id":null,"tags":["good_quality_picture","good_quality_thumbnail","immediate_payment","cart_eligible","shipping_guaranteed"]},{"id":"MLA705173222","site_id":"MLA","title":"Lentes Anteojos Marcos Vista Receta Aviador Armazones Optica","seller":{"id":135601643,"permalink":"http://perfil.mercadolibre.com.ar/RANDINC","registration_date":"2013-03-26T18:09:23.000-04:00",
            |"car_dealer":false,"real_estate_agency":false,"tags":["normal","user_info_verified","credits_active_borrower","credits_profile","eshop","mshops","messages_as_seller","messages_as_buyer"],"eshop":{"nick_name":"RANDINC","eshop_rubro":null,"eshop_id":172644,"eshop_locations":[],"site_id":"MLA","eshop_logo_url":"http://resources.mlstatic.com/eshops/135601643.png","eshop_status_id":2,"seller":135601643,"eshop_experience":0},"seller_reputation":{"transactions":{"total":4220,"canceled":349,"period":"historic","ratings":{"negative":0.01,"positive":0.99,"neutral":0},"completed":3871},"power_seller_status":"platinum","metrics":{"claims":{"rate":0.0074,"value":7,"period":"3 months"},"delayed_handling_time":{"rate":0.1121,"value":86,"period":"3 months"},"sales":{"period":"3 months","completed":857},"cancellations":{"rate":0.0042,"value":4,"period":"3 months"}},"level_id":"5_green"}},"price":899,"currency_id":"ARS","available_quantity":1,"sold_quantity":250,"buying_mode":"buy_it_now","listing_type_id":"gold_special","stop_time":"2038-01-26T04:00:00.000Z","condition":"new","permalink":"https://articulo.mercadolibre.com.ar/MLA-705173222-lentes-anteojos-marcos-vista-receta-aviador-armazones-optica-_JM","thumbnail":"http://mla-s1-p.mlstatic.com/712816-MLA41113597169_032020-I.jpg","accepts_mercadopago":true,"installments":{"quantity":12,"amount":127.49,"rate":70.17,"currency_id":"ARS"},"address":{"state_id":"AR-C","state_name":"Capital Federal","city_id":null,"city_name":"Floresta"},"shipping":{"free_shipping":false,"mode":"me2","tags":["self_service_in"],"logistic_type":"xd_drop_off","store_pick_up":false},
            |"seller_address":{"id":"","comment":"","address_line":"","zip_code":"","country":{"id":"AR","name":"Argentina"},"state":{"id":"AR-C","name":"Capital Federal"},"city":{"id":null,"name":"Floresta"},"latitude":"","longitude":""},"attributes":[{"value_id":"2373055","value_name":"Randy","attribute_group_id":"OTHERS","name":"Marca","value_struct":null,"values":[{"id":"2373055","name":"Randy","struct":null,"source":1505}],"attribute_group_name":"Otros","source":1505,"id":"BRAND"},{"attribute_group_name":"Otros","source":1,"id":"ITEM_CONDITION","name":"Condición del ítem","value_struct":null,"attribute_group_id":"OTHERS","value_id":"2230284","value_name":"Nuevo","values":[{"id":"2230284","name":"Nuevo","struct":null,"source":1}]},{"attribute_group_id":"OTHERS","attribute_group_name":"Otros","id":"MODEL","values":[{"struct":null,"source":1505,"id":null,"name":"Aviar"}],"value_name":"Aviar","value_struct":null,"source":1505,"name":"Modelo","value_id":null}],"original_price":null,"category_id":"MLA417127","official_store_id":null,"domain_id":"MLA-GLASSES_FRAMES","catalog_product_id":null,"tags":["brand_verified","good_quality_picture","good_quality_thumbnail","loyalty_discount_eligible","immediate_payment","cart_eligible","shipping_guaranteed"]},{"id":"MLA693617895","site_id":"MLA","title":"10 Mt Guirnalda Luces Calidas Lluvia  Armada Kermese Retro","seller":{"id":139802766,"permalink":"http://perfil.mercadolibre.com.ar/ELECTROMOL","registration_date":"2013-06-05T18:49:45.000-04:00","car_dealer":false,"real_estate_agency":false,"tags":["normal","user_info_verified","credits_profile","messages_as_seller","messages_as_buyer"],
            |"seller_reputation":{"transactions":{"total":25945,"canceled":481,"period":"historic","ratings":{"negative":0.01,"positive":0.98,"neutral":0.01},"completed":25464},"power_seller_status":"platinum","metrics":{"claims":{"rate":0.0134,"value":36,"period":"3 months"},"delayed_handling_time":{"rate":0.0046,"value":10,"period":"3 months"},"sales":{"period":"3 months","completed":2576},"cancellations":{"rate":0.0044,"value":12,"period":"3 months"}},"level_id":"5_green"}},"price":1499,"currency_id":"ARS","available_quantity":5000,"sold_quantity":500,"buying_mode":"buy_it_now","listing_type_id":"gold_special","stop_time":"2040-07-06T22:57:27.000Z","condition":"new","permalink":"https://articulo.mercadolibre.com.ar/MLA-693617895-10-mt-guirnalda-luces-calidas-lluvia-armada-kermese-retro-_JM","thumbnail":"http://mla-s2-p.mlstatic.com/927568-MLA41842142502_052020-I.jpg","accepts_mercadopago":true,"installments":{"quantity":12,"amount":212.57,"rate":70.17,"currency_id":"ARS"},"address":{"state_id":"AR-C","state_name":"Capital Federal","city_id":null,"city_name":"caba"},"shipping":{"free_shipping":false,"mode":"me2","tags":["self_service_in"],"logistic_type":"cross_docking","store_pick_up":false},"seller_address":{"id":"","comment":"","address_line":"","zip_code":"","country":{"id":"AR","name":"Argentina"},"state":{"id":"AR-C","name":"Capital Federal"},"city":{"id":null,"name":"caba"},"latitude":"","longitude":""},"attributes":[{"value_id":null,"value_struct":null,"source":5033071320073938,"id":"BRAND","value_name":"ELECTROLED","values":[{"id":null,"name":"ELECTROLED","struct":null,"source":5033071320073938}],
            |"attribute_group_id":"OTHERS","attribute_group_name":"Otros","name":"Marca"},{"value_name":"Nuevo","value_struct":null,"values":[{"id":"2230284","name":"Nuevo","struct":null,"source":1}],"attribute_group_id":"OTHERS","attribute_group_name":"Otros","value_id":"2230284","name":"Condición del ítem","source":1,"id":"ITEM_CONDITION"},{"value_struct":null,"values":[{"id":null,"name":"Gota","struct":null,"source":1572}],"source":1572,"id":"MODEL","name":"Modelo","value_id":null,"value_name":"Gota","attribute_group_id":"OTHERS","attribute_group_name":"Otros"}],"original_price":null,"category_id":"MLA378164","official_store_id":null,"domain_id":"MLA-HOME_DECOR","catalog_product_id":null,"tags":["brand_verified","good_quality_picture","good_quality_thumbnail","immediate_payment","cart_eligible","shipping_guaranteed"]}],"secondary_results":[],"related_results":[],"sort":{"id":"relevance","name":"Más relevantes"},"available_sorts":[{"id":"price_asc","name":"Menor precio"},{"id":"price_desc","name":"Mayor precio"}],"filters":[]}""".trimMargin()
    var responseEmpty =
        """{"site_id":"MLA","query":"noResults","paging":{"total":0,"offset":0,"limit":50,"primary_results":0},"results":[],"secondary_results":[],"related_results":[],"sort":{"id":"relevance","name":"Más relevantes"},"available_sorts":[{"id":"price_asc","name":"Menor precio"},{"id":"price_desc","name":"Mayor precio"}],"filters":[],"available_filters":[]}"""
    var responseFail = """{}"""


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
            // it.dispatcher = dispatcherForSearch()
        }

    }

    fun childAtPosition(
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


    fun dispatcherForSearch(): Dispatcher {
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
