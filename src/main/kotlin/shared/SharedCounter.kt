import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

var counter = 0

fun main(args: Array<String>) {
    runBlocking {
        val count = launch (Dispatchers.Default) {
            incrementORDecrement(action1 = {counter++}, action2 = {counter--})
        }
        count.join()
        println("Counter = $counter")
    }
}

suspend fun incrementORDecrement(k : Int = 10000, action1: suspend () -> Unit, action2: suspend () -> Unit){
    val time = measureTimeMillis {
        coroutineScope {
            launch { repeat(k) {action1()} }
            launch { repeat(k) {action2()} }
        }
    }
    println("Completed ${k} actions in $time ms")
}