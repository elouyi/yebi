import com.elouyi.build.mavenPublish

plugins {
    kotlin("jvm")
}

apply(from = rootProject.file("gradle/yebi-common.gradle"))
apply(from = rootProject.file("gradle/publishing.gradle"))

kotlin {
    sourceSets {
        val main by getting {
            dependencies {
                api(project(":yebi-core"))
            }
        }
    }
}

mavenPublish()
