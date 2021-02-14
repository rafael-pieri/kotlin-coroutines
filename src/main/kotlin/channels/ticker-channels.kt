package channels

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.ticker

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
fun main() {
    runBlocking {
        val tickerChannel = ticker(1000)
        launch {
            val startTime = System.currentTimeMillis()
            tickerChannel.consumeEach {
                val delta = System.currentTimeMillis() - startTime
                println("Received tick after $delta")
            }
        }

        delay(5000)

        tickerChannel.cancel()
    }
}