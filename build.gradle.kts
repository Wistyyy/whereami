plugins {
    kotlin("jvm") version "2.4.0"
    kotlin("plugin.serialization") version "2.4.0"
    application
}

group = "dev.xenoo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")
}

kotlin {
    jvmToolchain(25)
}

application {
    mainClass.set("dev.xenoo.whereami.MainKt")
}

tasks.test {
    useJUnitPlatform()
}