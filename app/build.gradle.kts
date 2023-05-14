import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
//    id("kotlin-parcelize")
}

android {
    namespace = "com.itis.newsviewer"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.itis.newsviewer"
        minSdk = 25
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    val keystoreProperties = Properties()
    keystoreProperties.load(FileInputStream(rootProject.file("keys.properties")))
    buildTypes.forEach {
        it.buildConfigField("String", "API_ENDPOINT", keystoreProperties.getProperty("baseUrl"))
        it.buildConfigField("String", "API_KEY", keystoreProperties.getProperty("apiKey"))
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    testImplementation("junit:junit:4.13.2")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("io.coil-kt:coil:2.2.2")

    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

    // region coroutines
    val coroutinesVersion = "1.6.4"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutinesVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${coroutinesVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${coroutinesVersion}")
    // endregion

    // region Network
    val retrofit = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit")
    implementation("com.squareup.retrofit2:adapter-rxjava3:$retrofit")

    val okhttp = "4.10.0"
    implementation("com.squareup.okhttp3:okhttp:$okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttp")

    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    // endregion

    // region Utils
    implementation("com.jakewharton.timber:timber:5.0.1")
    // endregion

    // region hilt
    val dagger = "2.44"
    implementation("com.google.dagger:hilt-android:${dagger}")
    annotationProcessor("com.google.dagger:hilt-compiler:${dagger}")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    //endregion

    val paging_version = "3.1.1"
    implementation("androidx.paging:paging-runtime-ktx:$paging_version")
    implementation("androidx.paging:paging-compose:1.0.0-alpha19")
}
