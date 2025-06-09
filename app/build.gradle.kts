plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.fansquad.ffdiatips.skinrewards"

    compileSdk = 35

    defaultConfig {
        applicationId = "com.fansquad.ffdiatips.skinrewards"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )


        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.browser)
    implementation(libs.core.ktx)
    implementation(libs.material)
    implementation(libs.ssp.android)
    implementation(libs.sdp.android)
    implementation("com.airbnb.android:lottie:6.6.2")
    implementation(libs.glide)
    implementation(libs.firebase.config)
    implementation(libs.gson)
    implementation("com.onesignal:OneSignal:[5.1.6, 5.1.99]")
    implementation(libs.appcompat)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

}
