
plugins {
    kotlin("jvm")
}

apply(from = rootProject.file("gradle/yebi-common.gradle"))

dependencies {
    api(project(":yebi-core"))
    api(project(":engine:yebi-engine-web"))
    api(project(":features:yebi-feature-live"))
    api(project(":features:yebi-feature-user"))
}
