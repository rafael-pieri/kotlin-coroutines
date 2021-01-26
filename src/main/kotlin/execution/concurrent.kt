package execution

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    println("Main program starts: ${Thread.currentThread().name}")

    val time = measureTimeMillis {
        /**
         * async: runs in a timeout.timeout.exception.main thread
         */
        val msgOne: Deferred<String> = async {
            println("First async Thread: ${Thread.currentThread().name}")
            getMessageOne()
        }
        val msgTwo: Deferred<String> = async {
            println("Second async Thread: ${Thread.currentThread().name}")
            getMessageTwo()
        }
        println("The entire message is: ${msgOne.await() + msgTwo.await()}")
    }

    println("Completed in $time ms")

    println("Main program ends: ${Thread.currentThread().name}")
}

private suspend fun getMessageOne(): String {
    delay(1000L)
    return "Hello "
}

private suspend fun getMessageTwo(): String {
    delay(1000L)
    return "World"
}
