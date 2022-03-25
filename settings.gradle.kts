
pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.aliyun.com/nexus/content/groups/public/")
        mavenCentral()
        google()
    }

    plugins {
        val versionsText = rootDir.resolve("buildSrc/src/main/kotlin/Versions.kt").readText()
        fun version(name: String): String {

            return versionsText.lineSequence()
                .map { it.trim() }
                .single { it.startsWith("const val $name") }
                .substringAfter('"', "")
                .substringBefore('"', "")
                .also {
                    check(it.isNotBlank())
                    logger.debug("$name=$it")
                }
        }

        kotlin("jvm") version version("kotlinCompiler")
        kotlin("plugin.serialization") version version("kotlinCompiler")
    }
}

include(":yebi-core")
include(":features:yebi-feature-live")

include(":engine:yebi-engine-web")