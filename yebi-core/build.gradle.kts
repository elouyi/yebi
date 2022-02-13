import com.elouyi.build.*

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

kotlin {

    explicitApi()

    sourceSets {

        all {
            languageSettings {
                optIn("kotlin.RequiresOptIn")
                optIn("kotlin.OptIn")
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
            }
        }
    }
}
