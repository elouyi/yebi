
plugins {
    kotlin("jvm")
}

apply(from = rootProject.file("gradle/yebi-common.gradle"))

kotlin {
    sourceSets {
        val main by getting {
            dependencies {
                api(project(":yebi-core"))
            }
        }
    }
}