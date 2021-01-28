package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * In case processing a flow takes a long time, a buffer is useful to accumulate flow values that can be processed later
 */
fun main() {
    runBlocking {
        /**
         * with buffer: add second value to buffer while it is processing the first value (parallel execution)
         *    e.g.: emit value 1
         *          emit value 2 and process value 1...
         *          emit value 3 and process value 2 and so on...
         *    approximate time (with 10 values): 4050 ms
         *
         * without buffer: emit value and than process it (sequential execution)
         *    e.g.: emit value 1
         *          process value 1
         *          emit value 2
         *          process value 2 and so on...
         *    approximate time (with 10 values): 3200 ms
         */
        val time = measureTimeMillis {
            generate()
                .buffer()
                .collect {
                    delay(300L)
                    println(it)
                }
        }
        println("Collected in $time ms")
    }
}

fun generate() = flow {
    for (i in 1..10) {
        delay(100L)
        emit(i)
    }
}