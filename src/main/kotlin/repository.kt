package com.example.repository

import com.example.model.Task
import com.example.model.TaskRequest

object TaskRepository {
    private val tasks = mutableListOf(
        Task(1, "Learn Ktor", true),
        Task(2, "Build a REST API", false),
        Task(3, "Write Unit Tests", false)
    )
    private var nextId = 4

    fun getAll(): List<Task> = tasks

    fun getById(id: Int): Task? = tasks.find { it.id == id }

    fun add(request: TaskRequest): Task {
        val task = Task(nextId++, request.content, request.isDone)
        tasks.add(task)
        return task
    }

    fun update(id: Int, request: TaskRequest): Boolean {
        val index = tasks.indexOfFirst { it.id == id }
        return if (index != -1) {
            tasks[index] = Task(id, request.content, request.isDone)
            true
        } else false
    }

    fun delete(id: Int): Boolean = tasks.removeIf { it.id == id }
}
