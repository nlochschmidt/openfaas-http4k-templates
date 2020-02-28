package com.openfaas.function

import com.natpryce.hamkrest.assertion.assertThat
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Request
import org.http4k.core.Status.Companion.OK
import org.http4k.hamkrest.hasBody
import org.http4k.hamkrest.hasStatus
import org.junit.jupiter.api.Test

internal class HandlerTest {

    val handler = Handler()

    @Test
    fun `on GET should respond with 200 and message`() {
        val response = handler(Request(GET, "/"))
        assertThat(response, hasStatus(OK))
        assertThat(response, hasBody("Hello, world!"))
    }

    @Test
    fun `on POST should respond with 200 and message`() {
        val response = handler(Request(POST, "/"))
        assertThat(response, hasStatus(OK))
        assertThat(response, hasBody("Hello, world!"))
    }
}