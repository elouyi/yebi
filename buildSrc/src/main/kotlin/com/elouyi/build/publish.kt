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
                        }
                        licenses {

                        }
                        developers {

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
        }
    }
}