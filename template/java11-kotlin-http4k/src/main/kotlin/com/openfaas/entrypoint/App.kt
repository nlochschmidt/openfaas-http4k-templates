package com.openfaas.entrypoint

import com.openfaas.function.Handler
import org.http4k.server.SunHttp
import org.http4k.server.asServer

fun main() {
    Handler().asServer(SunHttp(3000)).start().block()
}
