package flow.operators

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        transformOperator()
    }
}

suspend fun transformOperator() {
    (1..10).asFlow()
        .transform {
            emit("Emitting string value $it")
            emit(it)
        }
        .collect {
            println(it)
        }
}
