
plugins {
    kotlin("jvm")
}

apply(from = rootProject.file("gradle/yebi-common.gradle"))

dependencies {
    implementation(project(":yebi-core"))
    implementation(project(":engine:yebi-engine-web"))
    implementation(project(":features:yebi-feature-live"))
    implementation(project(":features:yebi-feature-user"))
}