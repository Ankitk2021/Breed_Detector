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



*   **Main Screen / Branding**

    <img src="https://github.com/user-attachments/assets/f0a748d9-a424-4784-a68e-64de75bc4db7" width="250">

*   **Image Selection (Gallery options)**

    <img src="https://github.com/user-attachments/assets/75fb93b8-469d-411c-8700-5910b236debf" width="250">

*   **Image Preview and "Get Details" Screen**

    <img src="https://github.com/user-attachments/assets/6dbfa021-dd79-434b-9a5f-2eda57e6897b" width="250">

*   **Results Display**

    <img src="https://github.com/user-attachments/assets/733d7a51-f4d6-42cf-a7c4-29d9a7448d2a" width="200">

    <img src="https://github.com/user-attachments/assets/b77d9359-1de2-4423-9051-b0268b4bb1a9" width="200">

*   **Search History**

    <img src="https://github.com/user-attachments/assets/4fe422a4-ff67-4639-b058-b3e09085f96b" width="200">


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