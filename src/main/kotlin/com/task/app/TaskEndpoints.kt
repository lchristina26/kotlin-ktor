package com.task.app

import com.fasterxml.jackson.annotation.JsonProperty
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TaskEndpoints(app: Application) {
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(TaskEndpoints::class.java)
    }

    init {
        app.routing {
            get("/user/{userId}/task") {
                val userId = call.parameters["userId"]!!.toInt()
                call.respond("you did it! $userId")
            }
        }
    }
}

data class Task(
        @JsonProperty("id")
        val id: Int,
        @JsonProperty("name")
        val name: String
)