plugins {
    kotlin("jvm") version "1.9.22"

    id("maven-publish")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.22"
}

group = "ru.lavafrai.mai.api"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jsoup:jsoup:1.10.2")
    // implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")

    implementation("io.ktor:ktor-client-core:2.3.9")
    implementation("io.ktor:ktor-client-cio:2.3.9")
    implementation("me.tongfei:progressbar:0.10.0")

    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("org.slf4j:slf4j-log4j12:2.0.12")
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