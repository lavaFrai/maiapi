plugins {
    kotlin("jvm") version "1.8.21"

    id("maven-publish")
}

group = "ru.lavafrai.mai.api"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jsoup:jsoup:1.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")

}

tasks.test {
    useJUnitPlatform()
    maxParallelForks = 6
}

kotlin {
    jvmToolchain(11)
}

publishing {
    publications {
        create<MavenPublication>("Maven") {
            groupId = group as String
            artifactId = "api"
            version = version as String
            from(components["kotlin"])
        }
    }
}