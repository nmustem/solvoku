plugins {
    alias(libs.plugins.kotlin.jvm)
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(project(":solvoku-service"))

    // Ktor test client
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.server.di)

    // Test framework
    testImplementation(libs.kotest.assertions.core)
    testImplementation(libs.kotest.assertions.json)
    testImplementation(libs.kotest.junit5)
    testImplementation(libs.ktor.serialization.kotlinx.json)
    testImplementation(libs.ktor.server.test.host)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}