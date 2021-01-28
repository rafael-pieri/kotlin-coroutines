package flow.exceptions

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/**
 * an exception can be caught by the operator .catch
 * catches any exception that occurs above the catch operator not below
 */
fun main() {
    runBlocking {
        catch()
    }
}

suspend fun catch() {
    (1..3).asFlow()
        .onEach { check(it != 2) }
        .catch { e -> println("Caught exception $e") }
        .collect { println(it) }
}
