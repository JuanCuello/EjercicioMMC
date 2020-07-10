# Ejercicio MMC



# Security
   Intended to use out of the box shrink and minification, Proguard
   // Out of scope Dexguard and/or R8

# Dependencies
    At the moment of writhing this the used (planned) dependencies are the follow:
    For persistence  we are adding Room Database, androidx.room:room-runtime version 2.2.5
    For rest communication we relay on the SQUARE's Retrofit2, com.squareup.retrofit2 version 2.9.0
    Crashalytics automated error report Crashalytics (and maybe remote config), com.google.firebase:firebase-analytics version 17.4.4
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:${project.LIFECYCLE_COMPONENTS_VERSION}"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:${project.LIFECYCLE_COMPONENTS_VERSION}"

    KOTLIN_VERSION=1.3.72
    ROOM_VERSION=2.2.5
    RETROFIT_VERSION=2.9.0
    FIREBASE_VERSION=17.4.4
    LIFECYCLE_COMPONENTS_VERSION=2.2.0


# Out of scope:
   - Analytics for data/behavior analysis.
   - Dexguard and dex2jar counter measures


# License
  Apache License 2.0