package com.example.routes

import com.example.repository.TaskRepository
import com.example.model.TaskRequest
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.http.*

fun Route.taskRoutes() {
    route("/tasks") {
        get {
            call.respond(TaskRepository.getAll())
        }

        get("{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            val task = id?.let { TaskRepository.getById(it) }

            if (task != null) {
                call.respond(task)
            } else {
                call.respond(HttpStatusCode.NotFound, "Task not found")
            }
        }

        post {
            val taskRequest = call.receive<TaskRequest>()
            val task = TaskRepository.add(taskRequest)
            call.respond(HttpStatusCode.Created, task)
        }

        put("{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            val taskRequest = call.receive<TaskRequest>()

            if (id == null || !TaskRepository.update(id, taskRequest)) {
                call.respond(HttpStatusCode.NotFound, "Task not found")
            } else {
                call.respond(HttpStatusCode.OK, "Task updated")
            }
        }

        delete("{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null || !TaskRepository.delete(id)) {
                call.respond(HttpStatusCode.NotFound, "Task not found")
            } else {
                call.respond(HttpStatusCode.NoContent)
            }
        }
    }
}
