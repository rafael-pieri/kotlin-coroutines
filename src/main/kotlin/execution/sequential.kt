package execution

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    println("Main program starts: ${Thread.currentThread().name}")

    val time = measureTimeMillis {
        val msgOne = getMessageOne()
        val msgTwo = getMessageTwo()
        println("The entire message is: ${msgOne + msgTwo}")
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
