package exception

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    println("Main program starts: ${Thread.currentThread().name}")

    val measureTimeMillis = measureTimeMillis {
        val job: Job = launch(Dispatchers.Default) {
            (0..500).forEach { i ->
                /**
                 * isActive: check if coroutine is active or not
                 */
                if (!isActive) {
                    return@launch
                }
                println("index: $i")
                @Suppress("BlockingMethodInNonBlockingContext")
                Thread.sleep(1)
            }
        }

        delay(10)
        job.cancelAndJoin()
    }

    println("time: ${measureTimeMillis / 1000}")

    println("Main program ends: ${Thread.currentThread().name}")
}
