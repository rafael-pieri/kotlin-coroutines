package scope

import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * coroutine scope: every coroutine has its own scope
 */
fun main() = runBlocking {
    println("runBlocking: $this")

    launch {
        println("launch: $this")
        launch {
            println("child launch: $this")
        }
    }

    @Suppress("DeferredResultUnused")
    async {
        println("async: $this")
    }

    print("... some other code...")
}
