import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

val atomicCounter = AtomicInteger() // from java.util.concurrent

fun main(args: Array<String>) {
    runBlocking {

        val count = GlobalScope.launch {
            massiveRun {
                atomicCounter.incrementAndGet()
            }
        }
        count.join()
        println("Counter = $atomicCounter")
    }
}