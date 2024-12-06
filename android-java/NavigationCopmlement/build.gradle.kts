

buildscript{
    repositories {
        google()

    }


    dependencies{
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.4")

    }
}
plugins {
    id("com.android.application") version "8.1.2" apply false
}