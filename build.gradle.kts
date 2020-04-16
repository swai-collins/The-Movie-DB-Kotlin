// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven(url = "https://maven.fabric.io/public")

    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.0.0-beta04")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.71")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.0-alpha05")
        classpath("com.google.gms:google-services:4.3.3")
        classpath("io.fabric.tools:gradle:1.31.2")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}
