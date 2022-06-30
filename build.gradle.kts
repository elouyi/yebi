
buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinCompiler}")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlinCompiler}")
        classpath("org.jetbrains.kotlinx:atomicfu-gradle-plugin:${Versions.kotlinxAtomicfu}")
    }
}

allprojects {
    group = "com.elouyi"
    version = Versions.yebi

    repositories {
        mavenCentral()
    }
}

