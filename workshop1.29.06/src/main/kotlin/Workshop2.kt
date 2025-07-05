package org.example  // ถ้ามี package อยู่แล้ว ให้ใส่ตามนี้ด้วย

data class Product(val name: String, val price: Double, val category: String)

fun main() {
    val products = listOf(
        Product("Laptop", 35000.0, "Electronics"),
        Product("Smartphone", 25000.0, "Electronics"),
        Product("T-shirt", 450.0, "Apparel"),
        Product("Monitor", 7500.0, "Electronics"),
        Product("Keyboard", 499.0, "Electronics"),
        Product("Jeans", 1200.0, "Apparel"),
        Product("Headphones", 1800.0, "Electronics")
    )

    println("=== สินค้าทั้งหมด ===")
    products.forEach { println(it) }

    val totalPrice = products
        .filter { it.category == "Electronics" }
        .filter { it.price > 500 }
        .map { it.price }
        .sum()

    println("\nผลรวมราคาสินค้า Electronics ที่ราคา > 500: $totalPrice บาท")

    println("\n=== สินค้าราคา < 1000 ===")
    products.filter { it.price < 1000 }.forEach { println(it) }

    println("\n=== สินค้าราคา 1000 - 9999 ===")
    products.filter { it.price in 1000.0..9999.0 }.forEach { println(it) }

    println("\n=== สินค้าราคา >= 10000 ===")
    products.filter { it.price >= 10000.0 }.forEach { println(it) }
}
