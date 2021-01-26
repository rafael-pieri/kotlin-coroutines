package timeout

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

fun main() = runBlocking {
    println("Main program starts: ${Thread.currentThread().name}")

    val result: String? = withTimeoutOrNull(2000) {
        (0..500).forEach { i ->
            println("$i")
            delay(500)
        }
        "I am done"
    }

    println("Result: $result")
    println("Main program ends: ${Thread.currentThread().name}")
}
