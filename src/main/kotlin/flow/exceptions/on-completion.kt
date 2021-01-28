package flow.exceptions

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/**
 * the equivalent of a finally block
 */
@ExperimentalCoroutinesApi
fun main() {
    runBlocking {
        onCompletion()
    }
}

@ExperimentalCoroutinesApi
suspend fun onCompletion() {
    (1..3).asFlow()
        .onEach { check(it != 2) }
        .onCompletion { e ->
            if (e != null)
                println("Flow completed with exception $e")
            else
                println("Flow completed successfully")
        }
        .catch { e -> println("Caught exception $e") }
        .collect { println(it) }
}
