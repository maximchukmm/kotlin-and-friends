package edu.kotlinlang.other.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory
import kotlin.concurrent.thread

private fun main() {
    val log = LoggerFactory.getLogger("MyLogger")

    log.info("Start")

    CoroutineScope(Dispatchers.Unconfined).launch {
        Thread.sleep(4000)
        log.info("1. Unconfined scope + Launch without scope")
    }

    CoroutineScope(Dispatchers.Unconfined).launch(Dispatchers.Unconfined) {
        Thread.sleep(4000)
        log.info("2. Unconfined scope + Unconfined launch")
    }

    CoroutineScope(Dispatchers.Default).launch {
        Thread.sleep(4000)
        log.info("3. Default scope + Launch without scope")
    }

    CoroutineScope(Dispatchers.Default).launch(Dispatchers.Default) {
        Thread.sleep(4000)
        log.info("4. Default scope + Default launch")
    }

    thread {
        Thread.sleep(4000)
        log.info("5. New thread")
    }

    log.info("End")
}