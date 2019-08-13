package com.openfaas.function

import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Request
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class HandlerTest {

    val handler = Handler()

    @Test
    fun `on GET should respond with 200 and message`() {
        val response = handler(Request(GET, "/"))
        assertEquals(200, response.status.code)
        assertEquals("Hello, world!", response.bodyString())
    }

    @Test
    fun `on POST should respond with 200 and message`() {
        val response = handler(Request(POST, "/"))
        assertEquals(200, response.status.code)
        assertEquals("Hello, world!", response.bodyString())
    }
}