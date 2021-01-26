package execution

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("Main program starts: ${Thread.currentThread().name}")

    /**
     * job: is used to manipulate a coroutine
     */
    val jobDeferred: Deferred<Int> = async {
        println("Fake work starts: ${Thread.currentThread().name}")
        delay(1000)
        println("Fake work starts: ${Thread.currentThread().name}")
        15
    }

    val await = jobDeferred.await()

    println("await: $await")

    println("Main program ends: ${Thread.currentThread().name}")
}