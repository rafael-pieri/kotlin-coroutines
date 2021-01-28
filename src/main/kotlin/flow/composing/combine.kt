package flow.composing

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/**
 * combine: match the latest value of one flow with the latest value of the other
 */
fun main() {
    runBlocking {
        combine()
    }
}

suspend fun combine() {
    val numbers = (1..5).asFlow().onEach { delay(300L) }
    val values = flowOf("One", "Two", "Three", "Four", "Five")
        .onEach { delay(400L) }
    numbers.combine(values) { a, b ->
        "$a -> $b"
    }
        .collect { println(it) }
}
