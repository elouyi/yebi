package com.elouyi.build

import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.*
import org.gradle.plugins.signing.SigningExtension

val org.gradle.api.Project.`sourceSets`: org.gradle.api.tasks.SourceSetContainer get() =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.getByName("sourceSets") as org.gradle.api.tasks.SourceSetContainer

/**
 * Configures the [sourceSets][org.gradle.api.tasks.SourceSetContainer] extension.
 */
fun org.gradle.api.Project.`sourceSets`(configure: Action<SourceSetContainer>): Unit =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("sourceSets", configure)
fun org.gradle.api.Project.`publishing`(configure: Action<PublishingExtension>): Unit =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("publishing", configure)

val org.gradle.api.Project.`signing`: org.gradle.plugins.signing.SigningExtension get() =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.getByName("signing") as org.gradle.plugins.signing.SigningExtension

/**
 * Configures the [signing][org.gradle.plugins.signing.SigningExtension] extension.
 */
fun org.gradle.api.Project.`signing`(configure: Action<SigningExtension>): Unit =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("signing", configure)


val org.gradle.api.Project.`publishing`: org.gradle.api.publish.PublishingExtension get() =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.getByName("publishing") as org.gradle.api.publish.PublishingExtension

fun Project.mavenPublish() {

    val ossName: String
    val ossPass: String
    val keyId: String
    val keyPass: String
    val keySec: String

    try {
        ossName = System.getenv("OSS_NAME") ?: error("")
        ossPass = System.getenv("OSS_PASS") ?: error("")
        keyId = System.getenv("KEY_ID") ?: error("")
        keyPass = System.getenv("KEY_PASS") ?: error("")
        keySec = decryptBase64(System.getenv("KEY_SEC") ?: error(""))
    } catch (_: Throwable) {
        println("no publish")
        return
    }
    afterEvaluate {

        publishing {
            val sourcesJar by tasks.registering(Jar::class) {
                archiveClassifier.set("sources")
                from(sourceSets["main"].allSource)
            }
            val doc = tasks.register("javadocJar", Jar::class) {
                archiveClassifier.set("javadoc")
            }

            publications {
                create<MavenPublication>("mavenJava") {
                    groupId = "com.elouyi"
                    artifactId = this@mavenPublish.name
                    version = Versions.yebi
                    from(components["kotlin"])

                    pom {
                        scm {
                            url.set("https://github.com/elouyi/yebi")
                            connection.set("scm:https://github.com/elouyi/yebi.git")
                            developerConnection.set("scm:git://github.com/elouyi/yebi.git")
                        }
                        licenses {
                            license {
                                name.set("MIT License")
                                url.set("https://github.com/elouyi/yebi/blob/dev/LICENSE")
                            }
                        }
                        developers {
                            developer {
                                id.set("elouyi")
                                name.set("ywnkm")
                                email.set("yw@elouyi.com")
                            }
                        }
                    }

                    pom.withXml {
                        asNode().apply {
                            appendNode("description", this@mavenPublish.description)
                            appendNode("name", this@mavenPublish.name)
                            appendNode("url", "https://github.com/elouyi/yebi")
                        }
                    }

                    artifact(sourcesJar.get())
                    artifact(doc.get())
                }
            }

            repositories {
                maven {
                    credentials {
                        name = "OSSRH"
                        setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                        username = ossName
                        password = ossPass
                    }
                }
            }
        }

        this@mavenPublish.setProperty("signing.keyId", keyId)
        this@mavenPublish.setProperty("signing.password", keyPass)
        this@mavenPublish.setProperty("signing.secretKeyRingFile", keySec)
        this@mavenPublish.setProperty("ossrhUsername", ossName)
        this@mavenPublish.setProperty("ossrhPassword", ossPass)
        signing {
            sign(publishing.publications["mavenJava"])
        }
    }
}