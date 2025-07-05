import kotlinx.coroutines.*

suspend fun fetchUserData(): String {
    delay(1000) // รอ 1 วินาที
    return "User data"
}

suspend fun fetchUserNotes(): String {
    delay(1000) // รออีก 1 วินาที
    return "User notes"
}

fun main() = runBlocking {
    println("Fetching data...")

    val userData = fetchUserData()
    println("Got user data: $userData")

    val userNotes = fetchUserNotes()
    println("Got user notes: $userNotes")

    println("All data fetched.")
}
