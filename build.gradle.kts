plugins {
    kotlin("jvm") version "1.8.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("io.javalin:javalin:5.5.0")
    implementation("org.slf4j:slf4j-simple:2.0.7")

    implementation("mysql:mysql-connector-java:8.0.28")
    implementation("org.litote.kmongo:kmongo:4.2.1")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("MainKt")
}