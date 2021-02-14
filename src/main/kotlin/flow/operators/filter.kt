package flow.operators

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.runBlocking

/**
 * operator:
 * - take an input flow, transform and return an output
 * - they are code
 * - the returning flow is synchronous
 */
fun main() {
    runBlocking {
        filterOperator()
    }
}

suspend fun filterOperator() {
    (1..10).asFlow()
        .filter {
            it % 2 == 0
        }
        .collect {
            println(it)
        }
}
