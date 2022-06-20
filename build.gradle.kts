
buildscript {
    repositories {
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
        mavenCentral()
    }
}
