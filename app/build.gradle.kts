plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "com.pirol541.basiclauncher"
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "0.9.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables{useSupportLibrary = true}

        signingConfigs {
            register("release") {
                storeFile = file("/home/steven/.android/android.keystore")
                storePassword = "123456"
                keyAlias = "AndroidApp"
                keyPassword = "123456"
            }
        }
    }

    buildTypes {
        named("release").configure {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile ("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }
        named("debug").configure {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile ("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions{
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Kotlin Libraries
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.72")

    // Support Libraries
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.0-beta7")

    // Arch Components
    implementation("androidx.core:core-ktx:1.5.0-alpha01")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("android.arch.navigation:navigation-fragment:1.0.0")
    implementation("androidx.room:room-runtime:2.2.5")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.2.0")
    kapt("androidx.room:room-compiler:2.2.5")

    //3rd party libs
    implementation("com.intuit.sdp:sdp-android:1.0.6")
    implementation("com.intuit.ssp:ssp-android:1.0.6")

    implementation("com.google.dagger:dagger-android:2.17")
    implementation("com.google.dagger:dagger-android-support:2.17")
    kapt("com.google.dagger:dagger-android-processor:2.17")
    kapt("com.google.dagger:dagger-compiler:2.17")

    // Test libs
    testImplementation("junit:junit:4.12")
    testImplementation("org.mockito:mockito-core:2.19.0")
    androidTestImplementation("androidx.room:room-testing:2.2.5")
    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestImplementation("androidx.test:rules:1.2.0")
    androidTestImplementation("androidx.annotation:annotation:1.1.0")
    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestImplementation("androidx.test:rules:1.2.0")
    androidTestImplementation("androidx.arch.core:core-testing:2.1.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.2.0")

}
