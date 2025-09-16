# Breed Detector 🐮

## Overview

Breed Detector is an Android application designed to identify the breed of cattle from an image captured with camera 📸 or picked from gallery 🖼️.The app displays the detected breed information. The application also keeps a history of recent searches (Just name of Breed of detected animal.).

## Features ✨

*   **Image Input:** 📸
    *   Capture photos using the device camera.
    *   Select images from the device gallery.
*   **Breed Detection:** 🤖
    *   Identifies cattle breeds from the provided image.
*   **Display Results:** 🐄
    *   Shows the detected breed information.
    *   Displays the input image.
*   **Search History:** 🔍
    *   Keeps a list of recent breed searches for easy reference.
*   **User Interface:** ✅
    *   Intuitive and user-friendly interface.
    *   Loading indicators during processing.
    *   Clear presentation of results.

## Screenshots

*(Consider adding screenshots of your app's main screens here)*

*   *Main Screen / Branding*
*   *Image Selection (Camera/Gallery options)*
*   *Image Preview and "Get Details" Screen*
*   *Results Display*
*   *Search History*

## Tech Stack & Dependencies 📚🧑‍💻

*   **UI:** 🌟
    *   Android XML Layouts
    *   Material Design Components (`com.google.android.material:material`)
    *   ConstraintLayout (`androidx.constraintlayout:constraintlayout`)
    *   RecyclerView (`androidx.recyclerview.widget.RecyclerView`) for displaying lists (responses and history).
    *   DrawerLayout (`androidx.drawerlayout.widget.DrawerLayout`) for navigation (for the search history).
    *   Lottie (`com.airbnb.android:lottie`) for animations (potentially used for loading states).
*   **Core:** 🏗️
    *   Kotlin (`androidx.core:core-ktx`) (A little bit)
    *   AndroidX Libraries (AppCompat, Activity, Core)
*   **Navigation:** 📲
    *   Android Jetpack Navigation Component (`androidx.navigation:navigation-fragment`, `androidx.navigation:navigation-ui`) (Implied by dependencies, though not explicitly used in the single XML shown).


*   **Data Handling:** 🛅
    *   `com.google.code.gson:gson` for JSON serialization/deserialization ( for API responses).
*   **Build System:** Gradle (Build.gradle.kts)
