package context

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * runBlocking: normally is used to make unit tests once it starts a coroutine scope
 */
fun main() = runBlocking {
    /**
     * launch: runs in a local scope, so if the execution of timeout.timeout.exception.main thread is destroyed, the coroutine is also interrupted.
     * This builder follows the concept fire and forget
     */
    launch {
        println("C1: ${Thread.currentThread().name}")
        delay(1000)
        println("C1 after delay: ${Thread.currentThread().name}")
    }

    /**
     * Dispatchers.Default — is used by all standard builders if no dispatcher or any other ContinuationInterceptor is specified in their context.
     * It uses a common pool of shared background threads.
     * This is an appropriate choice for compute-intensive coroutines that consume CPU resources.
     */
    launch(Dispatchers.Default) {
        println("C2: ${Thread.currentThread().name}")
        delay(1000)
        println("C2 after delay: ${Thread.currentThread().name}")
    }

    /**
     * Dispatchers.Unconfined — starts coroutine execution in the current call-frame until the first suspension, whereupon the coroutine builder function returns.
     * The coroutine will later resume in whatever thread used by the corresponding suspending function, without confining it to any specific thread or pool.
     * The Unconfined dispatcher should not normally be used in code.
     */
    launch(Dispatchers.Unconfined) {
        println("C3: ${Thread.currentThread().name}")
        delay(1000)
        println("C3 after delay: ${Thread.currentThread().name}")

        launch(coroutineContext) {
            println("C6: ${Thread.currentThread().name}")
            delay(1000)
            println("C6 after delay: ${Thread.currentThread().name}")
        }
    }

    /**
     * Dispatchers.IO — uses a shared pool of on-demand created threads and is designed for offloading of IO-intensive blocking operations
     * (like file I/O and blocking socket I/O).
     */
    launch(Dispatchers.IO) {
        println("C4: ${Thread.currentThread().name}")
        delay(1000)
        println("C4 after delay: ${Thread.currentThread().name}")
    }

    launch(coroutineContext) {
        println("C5: ${Thread.currentThread().name}")
        delay(1000)
        println("C5 after delay: ${Thread.currentThread().name}")
    }

    print("... some other code ...")
}
