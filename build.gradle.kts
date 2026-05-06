
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(ktorLibs.plugins.ktor)
}

repositories {
    mavenCentral()
}

// configure client library publishing
allprojects {
    group = "com.solvoku"
    version = "1.0.0-SNAPSHOT"
}


application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

kotlin {
    jvmToolchain(21)
}

