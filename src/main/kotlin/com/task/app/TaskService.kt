package com.task.app

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.JacksonConverter
import io.ktor.response.respond
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.slf4j.LoggerFactory
import org.slf4j.event.Level

object TaskService {

    private val logger = LoggerFactory.getLogger(TaskService::class.java)
    @JvmStatic
    fun main(args: Array<String>) {
        val server = embeddedServer(Netty, port = 8000) {
            install(CallLogging) {
                level = Level.INFO
            }

            install(StatusPages) {
                exception<Throwable> { t ->
                    logger.warn("Unhandled exception")
                    call.respond(HttpStatusCode.InternalServerError)
                }
            }

            install(ContentNegotiation) {
                register(ContentType.Application.Json, JacksonConverter())
            }

            TaskEndpoints(this)
        }

        server.start(wait = true)

    }
}

fun configuredObjectMapper() = ObjectMapper().apply {
    configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
    registerModule(KotlinModule())
}
