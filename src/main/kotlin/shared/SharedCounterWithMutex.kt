import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

val mutex = Mutex()

fun main(args: Array<String>) {
    runBlocking {
        val count = launch (Dispatchers.Default) {
            incrementORDecrement(action1 = {
                // protect each increment with lock
                mutex.withLock {
                    counter++
                }
            }, action2 = {
                // protect each increment with lock
                mutex.withLock {
                    counter--
                }
            })
        }
        count.join()
        println("Counter = $counter")
    }
}