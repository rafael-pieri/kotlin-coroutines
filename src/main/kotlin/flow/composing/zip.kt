package flow.composing

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

/**
 * zip: matches corresponding values of two flows
 */
fun main() {
    runBlocking {
        zip()
    }
}

suspend fun zip() {
    val english = flowOf("One", "Two", "Three")
    val french = flowOf("Un", "Deux", "Troix")
    english.zip(french) { a, b -> "'$a' in French is '$b'" }
        .collect {
            println(it)
        }
}