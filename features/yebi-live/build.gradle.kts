
plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

kotlin {

    explicitApi()

    sourceSets {

        val main by getting {
            dependencies {
                api(project(":yebi-core"))
                implementation(kotlin("stdlib"))
                implementation(kotlin("reflect"))
            }
        }
    }
}
