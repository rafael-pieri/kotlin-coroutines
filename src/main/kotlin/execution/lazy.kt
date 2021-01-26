package execution

import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Main program starts: ${Thread.currentThread().name}")

    val msgOne: Deferred<String> = async(start = CoroutineStart.LAZY) {
        println("First async Thread: ${Thread.currentThread().name}")
        getMessageOne()
    }

    val msgTwo: Deferred<String> = async(start = CoroutineStart.LAZY) {
        println("First async Thread: ${Thread.currentThread().name}")
        getMessageTwo()
    }

    println("The entire message is: ${msgOne.await() + msgTwo.await()}")

    println("Main program ends: ${Thread.currentThread().name}")
}

private suspend fun getMessageOne(): String {
    delay(1000L)
    println("After working in execution.getMessageOne()")
    return "Hello "
}

private suspend fun getMessageTwo(): String {
    delay(1000L)
    println("After working in execution.getMessageTwo()")
    return "World"
}
