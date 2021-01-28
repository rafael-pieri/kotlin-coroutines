package exception

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    println("Main program starts: ${Thread.currentThread().name}")

    val measureTimeMillis = measureTimeMillis {
        val job: Job = launch(Dispatchers.Default) {
            try {
                (0..500).forEach { i ->
                    println("index: $i")
                    /**
                     * It is only possible to suspend a coroutine if the method is cooperative.
                     * E.g.: delay(), yield(), withContext(), withTimeout()
                     */
                    delay(1)
                }
            } catch (ex: CancellationException) {
                /**
                 * It just possible capture suspending functions it it throws CancellationException.
                 */
                println("Exception caught safely: ${ex.message}")
            } finally {
                /**
                 * It is not possible run suspending function from the finally block because the execution is already cancelled.
                 * If it is necessary to execute suspending function from a finally block then wrap the code within withContext.
                 */
                withContext(NonCancellable) {
                    delay(1000)
                    println("Close resources in finally")
                }
            }
        }

        delay(100)
        /**
         * cancel: coroutine needs to be cooperative
         */
        job.cancel(CancellationException(("My own crash message")))
    }

    println("time: ${measureTimeMillis / 1000}")

    println("Main program ends: ${Thread.currentThread().name}")
}
