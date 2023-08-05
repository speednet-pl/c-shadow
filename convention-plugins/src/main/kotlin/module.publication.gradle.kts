import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.`maven-publish`

plugins {
    `maven-publish`
    signing
}

group = "pl.speednet.c-shadow"
version = "0.1.3"

publishing {
    // Configure all publications
    publications.withType<MavenPublication> {
        artifactId = "c-shadow"
        // Stub javadoc.jar artifact
        artifact(tasks.register("${name}JavadocJar", Jar::class) {
            archiveClassifier.set("javadoc")
            archiveAppendix.set(this@withType.name)
        })

        // Provide artifacts information requited by Maven Central
        pom {
            name.set("c-shadow")
            description.set("CSS alike shadow support for for Jetpack Compose & Multiplatform.")
            url.set("https://github.com/speednet-pl/c-shadow")

            licenses {
                license {
                    name.set("MIT")
                    url.set("https://opensource.org/licenses/MIT")
                }
            }
            developers {
                developer {
                    id.set("MikolajKakol")
                    name.set("Mikolaj Kakol")
                    email.set("mikolaj.kakol@gmail.com")
                }
            }
            scm {
                url.set("https://github.com/speednet-pl/c-shadow")
            }
        }
    }
}

signing {
    if (project.hasProperty("signing.gnupg.keyName")) {
        useGpgCmd()
        sign(publishing.publications)
    }
}