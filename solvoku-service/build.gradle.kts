plugins {
    alias(ktorLibs.plugins.kotlin.jvm)
    alias(ktorLibs.plugins.ktor)
}

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(ktorLibs.ktor.serialization.kotlinx.json)
    implementation(ktorLibs.ktor.server.config.yaml)
    implementation(ktorLibs.ktor.server.content.negotiation)
    implementation(ktorLibs.ktor.server.core)
    implementation(ktorLibs.ktor.server.netty)

    // Logging
    implementation("ch.qos.logback:logback-classic:1.5.6")
}