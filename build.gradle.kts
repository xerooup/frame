plugins {
    kotlin("jvm") version "2.3.0"
}

group = "io.github.xerooup"
version = "0.1.0"

repositories {
    mavenCentral()
}

java {
    withSourcesJar()
    withJavadocJar()
}

tasks.build {
    dependsOn("2d:build")
    dependsOn("2d:publishToMavenLocal")
}

kotlin {
    jvmToolchain(21)
}