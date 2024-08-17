plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
}
apply(from = "../shared_dependencies.gradle")

android {
    namespace = "com.k4nd4.capstone"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.k4nd4.capstone"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    dynamicFeatures += setOf(":favorite")
}

dependencies {
    implementation(project(":core"))

//    implementation("com.github.bumptech.glide:glide:$glideVersion")
//    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycleVersion")
//    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
//    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
//    ksp("androidx.lifecycle:lifecycle-compiler:$lifecycleVersion")
//
//    implementation("androidx.viewpager2:viewpager2:$viewPagerVersion")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.room.common)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}