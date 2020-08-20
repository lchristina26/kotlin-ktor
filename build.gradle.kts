plugins {
    java
    kotlin("jvm") version "1.3.72"
    id("application")
    id("nu.studer.jooq") version "4.2"
    id("org.flywaydb.flyway") version "6.5.5"
}

val dbUser by extra { "task-user" }
val dbPw by extra { "task-pw" }
val dbUrl by extra { "jdbc:postgresql://localhost:10001/task" }

apply(from = "jooq.gradle")

application {
    mainClassName = "com.task.app.TaskService"
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
    implementation("org.jooq:jooq")
    jooqRuntime("org.postgresql:postgresql:42.2.12")
    runtimeOnly("org.postgresql:postgresql:42.2.12")
}

flyway {
    url = dbUrl
    user = dbUser
    password = dbPw
    validateMigrationNaming = true
}

tasks {
    flywayMigrate {
        mustRunAfter(flywayClean)
    }

    // compile to java 8 bytecode, not java 6
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}
