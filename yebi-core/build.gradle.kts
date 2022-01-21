import com.elouyi.build.*

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

kotlin {

    explicitApi()

    sourceSets {

        val main by getting {

            dependencies {
                implementation(kotlin("stdlib"))
                implementation(kotlin("reflect"))
                api(kotlinx("serialization-json", Versions.kotlinxSerialization))
                api(kotlinx("coroutines-core", Versions.kotlinxCoroutines))
            }
        }
    }
}
