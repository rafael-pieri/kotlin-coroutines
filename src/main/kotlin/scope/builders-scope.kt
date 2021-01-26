package scope

import kotlinx.coroutines.*

fun main() {
    println("Program execution will now block")

    /**
     * runBlocking: runs in a timeout.timeout.exception.main thread
     */
    runBlocking {
        launch {
            delay(1000L)
            println("runBlocking Thread: ${Thread.currentThread().name}")
        }

        /**
         * GlobalScope: runs in a application scope
         */
        GlobalScope.launch {
            delay(500L)
            println("GlobalScope.launch Thread: ${Thread.currentThread().name}")
        }

        /**
         * coroutineScope: runs in a timeout.timeout.exception.main thread
         */
        coroutineScope {
            launch {
                delay(1500L)
                println("coroutineScope Thread: ${Thread.currentThread().name}")
            }
        }
    }

    println("Program execution will now continue")
}
