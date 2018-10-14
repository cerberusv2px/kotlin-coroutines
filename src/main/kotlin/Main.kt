import kotlinx.coroutines.*

fun main(args: Array<String>) {
    // exampleBlocking()
    //exampleBlockingAlternative()
    //exampleBlockingDispatcher()
    //exampleLaunchGlobalWaiting()
    exampleLaunchCoroutineScope()
}

suspend fun printlnDelayed(message: String) {
    delay(1000)
    println(message)
}

fun exampleBlocking() {
    println("one")
    runBlocking {
        printlnDelayed("two")
    }
    println("three")
}

fun exampleBlockingAlternative() = runBlocking {
    println("one")
    printlnDelayed("two")
    println("three")
}

fun exampleBlockingDispatcher() {
    runBlocking(Dispatchers.Default) {
        println("one - from thread ${Thread.currentThread().name}")
        printlnDelayed("two - from thread ${Thread.currentThread().name}")
    }
    // Outside of runBlocking to show that irs running in the blocked main thread
    println("three - from thread ${Thread.currentThread().name}")
    // it still runs only after the runBlocking is fully executed.
}

fun exampleLaunchGlobal() = runBlocking {
    println("one - from thread ${Thread.currentThread().name}")
    GlobalScope.launch {
        printlnDelayed("two - from thread ${Thread.currentThread().name}")
    }
    println("three - from thread ${Thread.currentThread().name}")
    delay(3000)
}

fun exampleLaunchGlobalWaiting() = runBlocking {
    println("one - from thread ${Thread.currentThread().name}")
    val job = GlobalScope.launch {
        printlnDelayed("two - from thread ${Thread.currentThread().name}")
    }
    println("three - from thread ${Thread.currentThread().name}")
    job.join()
}

fun exampleLaunchCoroutineScope() = runBlocking {
    println("one - from thread ${Thread.currentThread().name}")
    launch(Dispatchers.Default){
        printlnDelayed("two - from thread ${Thread.currentThread().name}")
    }
    println("three - from thread ${Thread.currentThread().name}")

}