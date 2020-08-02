### Ejercicio MMC
Android studio 4, Gradle 4, Kotlin 1.3.72, compile sdk 29

# This use livedata through the repository concept centered on the search result ViewModel,
 persist the data from the server

# Testing:
    * Unit: j4uit/mockito
    * Integration: To perform the end to end testing including the retrofit gson to models conversion, a real device or emulator that can reach host localhost is required , this is to be able to connect to MockWebServer ip address on the localhost.

# Security
   Intended to use out of the box shrink and minification, and proguard rules for R8

# Dependencies
    At the moment of writhing this the used (planned) dependencies are the follow:
    Lifecycle components version 2.2.0, lifecycle-livedata-ktx and lifecycle-viewmodel-ktx
    For persistence  we are adding Room Database, androidx.room:room-runtime version 2.2.5
    For rest communication we relay on the SQUARE's Retrofit2, com.squareup.retrofit2 and converter-gson version 2.9.0
    Crashalytics automated error report Crashalytics (and maybe remote config), com.google.firebase:firebase-analytics version 17.4.4
    Mock server from squareup com.squareup.okhttp3:mockwebserver current version 4.8.0
    Picasso image loader is used for remote image fetching com.squareup.picasso current version 2.71828
    For limit the scope I use com.shouquan:StateLayout to get a state layout
    Mockito for testing "org.mockito:mockito-core" version 3.3.3

# Out of scope:
   - Drawable animation, design
   - Grid layout manager to present on landscape layout
   - Analytics for data/behavior analysis.
   - Dexguard and dex2jar counter measures

# License
  Apache License 2.0