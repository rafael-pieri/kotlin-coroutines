package flow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

fun main() {
    runBlocking {
        val numbersFlow = sendNewNumbers()
        println("Flow hasn't started yet")
        println("Starting flow now")
        withTimeoutOrNull(1000L) {
            numbersFlow.collect { println(it) }
        }
    }
}

private fun sendNewNumbers() = listOf(1, 2, 3).asFlow()
