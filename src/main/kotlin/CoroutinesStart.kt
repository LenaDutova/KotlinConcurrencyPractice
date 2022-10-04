import kotlinx.coroutines.*

/**
 * Корутины предварительно подключаются к проекту:
 * 1. File - Project Structure
 * 2. Libraries - "+" - From Maven
 * 3. "kotlinx-coroutines-core-jvm" - search - ok - apply
 *
 * Корутины = сопрограммы
 */
fun main(args: Array<String>){
    println("Hi!")

    /*
       Создание корутин для разных целей:
       - launch (запустить и забыть),
       - async (вернуть обещанное вызовом await),

       Контексты корутины:
       - coroutineScope (создает пользовательский контекст; ожидает выполнения всех определенных внутри нее корутин; требует объявления функции как suspend)
       - runBlocking (блокирует текущий поток)

       - GlobalScope (время жизни корутины ограничено временем жизни приложения)
       - MainScope (UI)
    */

    // синхронный запуск, т.е. ожидаем завершения работы
//    runBlocking {
//        launch { counterProcedure() }     // пусть запуститься
//
//        val result = async { counterFunction() }.await() // запуститься и вернет результат
//        println("Get result from corutine = $result")
//    }

    // синхронный запуск множества последовательных корутин
//    runBlocking {
//        repeat(10) {// запуск большого количества корутин
//            val longLiver = launch { counterProcedure(1000) }
////            longLiver.cancel() // можем отменить выполнение корутины
//        }
//    }

    // изменение контекста при запуске корутин
//    runBlocking {
//        // без параметров - контекст родителя, в этом примере runBlocking
//        launch { counterProcedure() }
//
//        // можно задать контектс корутины, чтобы отделить ее от главного потока
//        launch (Dispatchers.Unconfined) { counterProcedure() } // будет работать с основным потоком
//        launch (Dispatchers.Default) { counterProcedure() } // // будет отправлено в DefaultDispatcher
//        launch (newSingleThreadContext("kukusiki")){ counterProcedure() }
//    }

    // синхронный запуск множества асинхронных корутин
//    runBlocking {
//        repeat(10) {// запуск большого количества корутин
//            launch (Dispatchers.Default) { counterProcedure() }
//        }
//    }

//    val job = GlobalScope.launch { // запуск новой корутины в фоне
//        counterProcedure()
//    }
//    job.join() // ждем завершения вложенной корутины

    println("Bye!")

    // В отличие от потоков, приложение не обязано ждать завершения корутин
    // По-этому мы приостанавливаем основной поток приложения
    Thread.sleep(500);
}

/**
 * Процедура, которая выводит числа от 0 до 50
 * Явно указано, что:
 * - функция не имеет возвращаемых параметров - Unit
 * - функция может быть прерван - suspend
 */
suspend fun counterProcedure (count : Int = 10, delay: Long = 0): Unit{
    for (i in 0..count) {
        println("${Thread.currentThread().name} $i")
        delay(delay)
    }
}

/**
 * Функция, вычисляющая сумму последовательных чисел от 0 до 50
 * Явно указано, что:
 * - функция возвращает целочисленное значение - Int
 * - функция может быть прерван - suspend
 */
suspend fun counterFunction (count : Int = 10, delay: Long = 0): Int{
    var result = 0
    for (i in 0..count) {
        result += i
        println("${Thread.currentThread().name} $result")
        delay(delay)
    }
    return result
}