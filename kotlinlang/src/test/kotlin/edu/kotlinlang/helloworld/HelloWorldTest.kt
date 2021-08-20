package edu.kotlinlang.helloworld

import org.junit.Test
import kotlin.test.assertEquals

class HelloWorldTest {

    @Test
    fun helloWorldTest() {
        assertEquals("Hello, world!", HelloWorld().sayHello())
    }

    private class HelloWorld {
        fun sayHello() = "Hello, world!"
    }
}
