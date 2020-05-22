plugins {
    java
    kotlin("jvm") version "1.3.72"
    id("application")
}

application {
    mainClassName = "co.protegee.app.ProtegeeService"
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.slf4j:slf4j-api:1.7.30")
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("io.ktor:ktor-server-netty:1.3.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.11.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.11.0")
    implementation("io.ktor:ktor-jackson:1.3.2")
}
