
buildscript {
    repositories {
        maven("https://maven.aliyun.com/nexus/content/groups/public/")
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinCompiler}")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlinCompiler}")
    }
}

allprojects {
    group = "com.elouyi"
    version = Versions.yebi

    repositories {
        maven("https://maven.aliyun.com/nexus/content/groups/public/")
        mavenCentral()
    }
}
