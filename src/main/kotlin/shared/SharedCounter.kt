import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

var counter = 0

fun main(args: Array<String>) {
    runBlocking {
        val count = launch (Dispatchers.Default) {
            massiveRun {
                counter++
            }
        }
        count.join()
        println("Counter = $counter")
    }
}

suspend fun massiveRun(action: suspend () -> Unit) {
    val n = 100  // number of coroutines to launch
    val k = 1000 // times an action is repeated by each coroutine

    val time = measureTimeMillis {
        coroutineScope { // scope for coroutines
            repeat(n) {
                launch {
                    repeat(k) { action() }
                }
            }
        }
    }
    println("Completed ${n * k} actions in $time ms")
}