package channels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
fun main() {
    runBlocking {
        val numbers = produceNumbers()
        val squares = square(numbers)
        for (i in 1..5) {
            println(squares.receive())
        }
        coroutineContext.cancelChildren()
    }
}

@ExperimentalCoroutinesApi
fun CoroutineScope.produceNumbers() = produce {
    var x = 1
    while (true)
        send(x++)
}

@ExperimentalCoroutinesApi
fun CoroutineScope.square(numbers: ReceiveChannel<Int>) = produce {
    for (x in numbers)
        send(x * x)
}