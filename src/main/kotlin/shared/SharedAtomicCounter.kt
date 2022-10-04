import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

val atomicCounter = AtomicInteger() // from java.util.concurrent

fun main(args: Array<String>) {
    runBlocking {

        val count = launch (Dispatchers.Default) {
            incrementORDecrement(action1 = {atomicCounter.incrementAndGet()}, action2 = {atomicCounter.decrementAndGet()})
        }
        count.join()
        println("Counter = $atomicCounter")
    }
}