@file:Suppress("unused_variable")

import com.elouyi.build.*

plugins {
    kotlin("jvm")
}

apply(from = rootProject.file("gradle/yebi-common.gradle"))
apply(from = rootProject.file("gradle/publishing.gradle"))

kotlin {

    sourceSets {

        all {
            languageSettings {
                optIn("kotlin.contracts.ExperimentalContracts")
            }
        }

        val main by getting {

            dependencies {
                implementation(kotlin("stdlib"))
                implementation(kotlin("reflect"))
                api(kotlinx("serialization-json", Versions.kotlinxSerialization))
                api(kotlinx("coroutines-core", Versions.kotlinxCoroutines))
                api("org.jetbrains.kotlinx:atomicfu:${Versions.kotlinxAtomicfu}")
                api("io.ktor:ktor-client-core:${Versions.ktor}")
                api("io.ktor:ktor-client-cio:${Versions.ktor}")
                api("io.ktor:ktor-client-serialization:${Versions.ktor}")
                api("io.github.microutils:kotlin-logging:${Versions.logging}")
                api("ch.qos.logback:logback-classic:${Versions.logback}")
            }
        }
    }
}
