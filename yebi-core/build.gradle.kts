@file:Suppress("unused_variable")

plugins {
    kotlin("multiplatform")
}

apply(from = rootProject.file("gradle/yebi-common.gradle"))
apply(from = rootProject.file("gradle/compile-multiplatform.gradle"))

kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {
                api("io.github.microutils:kotlin-logging:${Versions.logging}")
            }
        }
    }
}
