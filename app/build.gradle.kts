plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
}

android {
    namespace = "com.example.urmetro"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.urmetro"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    viewBinding{
        enable=true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation ("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation ("androidx.navigation:navigation-ui-ktx:2.5.3")

    implementation ("com.airbnb.android:lottie:3.4.0")

    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")

    implementation ("com.github.bumptech.glide:glide:4.12.0")
    kapt ("com.github.bumptech.glide:compiler:4.12.0")

    implementation ("androidx.recyclerview:recyclerview:1.3.0")
    implementation ("androidx.recyclerview:recyclerview-selection:1.1.0")

    //ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0-rc01")
    //LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0-rc01")
    //by viewModels extension for Activity
    implementation("androidx.activity:activity-ktx:1.3.1")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")

    implementation ("io.github.rburgst:okhttp-digest:3.0.1")


    implementation ("androidx.camera:camera-core:1.1.0-beta02")
    implementation ("androidx.camera:camera-camera2:1.1.0-beta02")
    implementation ("androidx.camera:camera-lifecycle:1.1.0-beta02")
    implementation ("androidx.camera:camera-view:1.1.0-beta02")
    implementation ("androidx.camera:camera-extensions:1.1.0-beta02")
}