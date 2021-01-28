package flow.operators

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        flowOnOperator()
    }
}

suspend fun flowOnOperator() {
    (1..10).asFlow()
        .flowOn(Dispatchers.IO)
        .collect {
            println(it)
        }
}
