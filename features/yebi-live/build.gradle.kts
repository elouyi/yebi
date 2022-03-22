
plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

kotlin {

    explicitApi()

    sourceSets {

        all {
            languageSettings {
                optIn("kotlin.contracts.ExperimentalContracts")

                optIn("kotlin.RequiresOptIn")
                optIn("kotlin.OptIn")
            }
        }

        val main by getting {
            dependencies {
                api(project(":yebi-core"))
                implementation(kotlin("stdlib"))
                implementation(kotlin("reflect"))
            }
        }
    }
}
