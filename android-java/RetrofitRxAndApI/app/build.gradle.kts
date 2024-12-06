plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.tnqr.retrofitrxandapi"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tnqr.retrofitrxandapi"
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
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation (com.squareup.retrofit2:retrofit:$retrofitVersion)
    implementation "com.squareup.retrofit2:converter-gson:$retrofitVersion"
    implementation "com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion"

    implementation "io.reactivex.rxjava2:rxjava:$rxJavaVersion"
    implementation "io.reactivex.rxjava2:rxandroid:$rxJavaVersion"
    implementation 'androidx.recyclerview:recyclerview:1.1.0'

   
}