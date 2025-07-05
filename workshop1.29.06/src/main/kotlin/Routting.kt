juary
juaryqz
กำลังฟัง ลุลา

นี่คือจุดเริ่มต้นของ #โจรงาน แชนแนลส่วนตัวนี้
juary — 25/5/2568 0:24
https://onlinetraining.dsd.go.th/courseinfo.php?category=2&course=191901
RIBØMBEE — 25/5/2568 0:25
จ้า
praew_xx444 — 27/6/2568 14:47
https://www.jetbrains.com/shop/download/II/2025100?_gl=1*duocx3*_gcl_au*MTEzMzk4ODU0My4xNzUxMDA3OTA1LjcxOTAxNTgwOC4xNzUxMDA4ODI4LjE3NTEwMDg4Njk.*FPAU*MzE0ODcyNjYyLjE3NTEwMDY5MTA.*_ga*MTg4NzkyMjAxNS4xNzUxMDA2OTEw*_ga_9J976DJZ68*czE3NTEwMDY5MDckbzEkZzEkdDE3NTEwMDk2ODYkajM2JGwwJGgw&fbclid=IwY2xjawLLKh5leHRuA2FlbQIxMABicmlkETFicDR5N2Z1RWdoSms1MWpiAR6Lhlt1iaJ0h1jIBM94ykpD2sd1g3KBHpb-UR1nFTSpLDILJbhVLe1XaRrhiw_aem_KPwT5fjhCr6fl-PkmHLTLA
nong poo — 14:18
rouiing
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
ย่อ
message.txt
2 KB
model
package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class Task(val id: Int, val content: String, val isDone: Boolean)
ขยาย
message.txt
1 KB
repository
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
ย่อ
repository.kt
1 KB
App
package com.example

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.routing.*
import com.example.routes.taskRoutes
import io.ktor.server.response.respondText

fun main() {
    embeddedServer(Netty, port = 8080) {
        module()
    }.start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }

    routing {
        get("/") {
            call.respondText("Hello AUM CAMT CMU!")
        }
        taskRoutes()
    }
}
ย่อ
message.txt
1 KB
RIBØMBEE — 15:23
workshop3
import org.example.Product
import kotlin.test.Test
import kotlin.test.assertEquals
import org.example.celsiusToFahrenheit
import org.example.kilometersToMiles
import org.example.calculateTotalElectronicsPriceOver500
import org.example.countElectronicsOver500


class WorkshopTest {

    val products = listOf(
        Product("Laptop", 35000.0, "Electronics"),
        Product("Smartphone", 25000.0, "Electronics"),
        Product("T-shirt", 450.0, "Apparel"),
        Product("Monitor", 7500.0, "Electronics"),
        Product("Keyboard", 499.0, "Electronics"), // ราคาไม่เกิน 500
        Product("Jeans", 1200.0, "Apparel"),
        Product("Headphones", 1800.0, "Electronics")  // ตรงตามเงื่อนไข
    )

    // --- Tests for Workshop #1: Unit Converter ---

    // celsius input: 20.0
    // expected output: 68.0
    @Test
    fun `test celsiusToFahrenheit with positive value`() {
        // Arrange: ตั้งค่า input และผลลัพธ์ที่คาดหวัง
        val celsiusInput = 20.0
        val expectedFahrenheit = 68.0

        // Act: เรียกใช้ฟังก์ชันที่ต้องการทดสอบ
        val actualFahrenheit = celsiusToFahrenheit(celsiusInput)
        // Assert: ตรวจสอบว่าผลลัพธ์ที่ได้ตรงกับที่คาดหวัง
        assertEquals(expectedFahrenheit, actualFahrenheit, 0.001, "20°C should be 68°F")
    }

    // celsius input: 0.0
    // expected output: 32.0
    @Test
    fun `test celsiusToFahrenheit with zero`() {
        // Act
        val actualFahrenheit = celsiusToFahrenheit(0.0)
        // Assert
        assertEquals(32.0, actualFahrenheit, 0.001, "0°C should be 32°F")
    }

    // celsius input: -10.0
    // expected output: 14.0
    @Test
    fun `test celsiusToFahrenheit with negative value`() {
        // Act
        val actualFahrenheit = celsiusToFahrenheit(-10.0)
        // Assert
        assertEquals(14.0, actualFahrenheit, 0.001, "-10°C should be 14°F")

    }

    // test for kilometersToMiles function
    // kilometers input: 1.0
    // expected output: 0.621371
    @Test
    fun `test kilometersToMiles with one kilometer`() {
        // Act
        val actualMiles = kilometersToMiles(1.0)
        // Assert
        assertEquals(0.621371, actualMiles, 0.000001, "1 km should be ~0.621371 miles")
    }

    // --- Tests for Workshop #2: Data Analysis Pipeline ---

    // จงเขียน test cases สำหรับฟังก์ชันนี้ โดยตรวจสอบผลรวมราคาสินค้า Electronics ที่ราคา > 500 บาท
    @Test
    fun `test calculateTotalElectronicsPriceOver500`() {
        val products = listOf(
            Product("Laptop", 600.0, "Electronics"),
            Product("Mouse", 50.0, "Electronics"),
            Product("TV", 1000.0, "Electronics"),
            Product("Book", 700.0, "Books")
        )
        val total = calculateTotalElectronicsPriceOver500(products)
        assertEquals(1600.0, total, 0.001, "Sum of Electronics >500 should be 1600.0")
    }

    @Test
    fun `test countElectronicsOver500`() {
        val products = listOf(
            Product("Laptop", 600.0, "Electronics"),
            Product("Mouse", 50.0, "Electronics"),
            Product("TV", 1000.0, "Electronics"),
            Product("Headphones", 550.0, "Electronics"),
            Product("Book", 700.0, "Books")
        )
        val count = countElectronicsOver500(products)
        assertEquals(3, count, "There should be 3 Electronics items over 500")
    }
}



... (14 บรรทัด)
ย่อ
message.txt
5 KB
workshop1.kt
package org.example

// Workshop #1: Simple Console Application - Unit Converter

fun main() {
    // 2. ใช้ while (true) เพื่อให้โปรแกรมทำงานวนซ้ำ
    ขยาย
    message.txt
    4 KB
            workshop2.kt
    package org.example

    // 1. กำหนด data class สำหรับเก็บข้อมูลสินค้า
    data class Product(val name: String, val price: Double, val category: String)
    fun calculateTotalElectronicsPriceOver500(products: List<Product>): Double =
        products
    ขยาย
    message.txt
    5 KB
    ﻿
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