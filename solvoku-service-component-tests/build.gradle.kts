plugins {
    alias(ktorLibs.plugins.kotlin.jvm)
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(project(":solvoku-service"))

    // Ktor test client
    implementation(ktorLibs.ktor.client.cio)
    implementation(ktorLibs.ktor.client.content.negotiation)
    implementation(ktorLibs.ktor.client.core)
    implementation(ktorLibs.ktor.serialization.kotlinx.json)

    // Test framework
    testImplementation(ktorLibs.ktor.server.test.host)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}