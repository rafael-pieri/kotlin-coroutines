package channels

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

@ExperimentalCoroutinesApi
fun main() {
    runBlocking {
        val producer = produceNewNumbers()
        repeat(5) { launchProcessor(it, producer) }
        delay(3000L)
        producer.cancel()
    }
}

@ExperimentalCoroutinesApi
fun CoroutineScope.produceNewNumbers() = produce {
    var x = 1
    while (true) {
        send(x++)
        delay(100L)
    }
}

fun CoroutineScope.launchProcessor(id: Int, channel: ReceiveChannel<Int>) =
    launch {
        for (message in channel)
            println("Processor $id received $message")
    }