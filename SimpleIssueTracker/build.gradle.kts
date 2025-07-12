// ✅ ต้องประกาศก่อน plugins
val kotlin_version = "1.9.22"
val ktor_version = "2.3.7"
val logback_version = "1.2.11"

plugins {
    kotlin("jvm") version kotlin_version
    application
    kotlin("plugin.serialization") version kotlin_version
}

group = "com.example"
version = "1.0"

application {
    mainClass.set("com.example.ApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")

    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}
