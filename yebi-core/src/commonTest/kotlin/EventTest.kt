package com.elouyi.yebi.test

import com.elouyi.yebi.YebiBot
import com.elouyi.yebi.engine.Web
import com.elouyi.yebi.event.EventPriority
import com.elouyi.yebi.event.YebiEvent
import com.elouyi.yebi.feature.user.UserFeature
import kotlinx.coroutines.delay
import mu.KotlinLogging
import kotlin.test.Test

class EventTest {

    class Foo(val id: Int)
    @Test
    fun foo() {
        println("test")
        val logger = KotlinLogging.logger {  }
        logger.debug { "test" }
        logger.info { "huag" }
        logger.warn { "test" }
    }

    @Test
    fun testEvent() {
        val r = kotlin.runCatching {
            println("test start")
            val event = YebiEvent.create<Int>()

            val l1 = event.subscribe(priority = EventPriority.LOW) {
                println("l1 with value $it")
                delay(200)
                println("l1 after delay 200ms")
            }
            event.subscribe(priority = EventPriority.NORMAL) {
                println("l4 with value $it")
                delay(100)
                println("l4 after delay 100ms")
            }
            val l2 = event.subscribe(priority = EventPriority.LOW) {
                println("l2 with value $it")
                delay(100)
                println("l2 after delay 100ms")
            }

            val l3 = event.subscribe(priority = EventPriority.HIGH) {
                println("l3 with value $it")
                delay(100)
                println("l3 after delay 100ms")
            }
            event(30)
            l1.cancel()

        }
        println(r)
    }
}