dependencies {
    implementation(ktorLibs.server.config.yaml)
    implementation(ktorLibs.server.core)
    implementation(ktorLibs.server.netty)
    implementation(libs.logback.classic)

    testImplementation(kotlin("test"))
    testImplementation(ktorLibs.server.testHost)
}

application {
    mainClass = "com.solvoku.solvokuservice.AppKt"
}

tasks.test {
    useJUnitPlatform()
}