package com.openfaas.entrypoint

import org.http4k.client.OkHttp
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.concurrent.thread

internal class AppKtTest {
    @Test
    fun `app starts`() {
        lateinit var serverThread: Thread
        try {
            serverThread = thread {
                try {
                    main()
                } catch (e: InterruptedException) { }
            }

            val response = OkHttp()(Request(GET, "http://localhost:3000"))

            assertEquals(200, response.status.code)
            assertEquals("Hello, world!", response.bodyString())
        } finally {
            serverThread.interrupt()
            serverThread.join()
        }
    }
}