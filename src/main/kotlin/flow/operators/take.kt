package flow.operators

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        takeOperator()
    }
}

suspend fun takeOperator() {
    (1..10).asFlow()
        .take(4)
        .collect {
            println(it)
        }
}
