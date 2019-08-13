package com.openfaas.entrypoint

import com.openfaas.function.Handler
import org.http4k.server.ApacheServer
import org.http4k.server.asServer

fun main() {
    Handler().asServer(ApacheServer(3000)).start().block()
}
