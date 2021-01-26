package timeout

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

fun main() = runBlocking {
    println("Main program starts: ${Thread.currentThread().name}")

    withTimeout(2000) {
        try {
            (0..500).forEach { i ->
                println("$i")
                delay(500)
            }
        } catch (ex: TimeoutCancellationException) { /* extends CancellationException */
            println("Error: ${ex.message}")
        } finally {
            println("Executing finally block")
        }
    }

    println("Main program ends: ${Thread.currentThread().name}")
}
