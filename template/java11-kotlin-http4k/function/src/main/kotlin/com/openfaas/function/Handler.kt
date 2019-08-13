package com.openfaas.function

import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK

/**
 * This handler gets instantiated by the OpenFaaS template.
 * Do not rename or move this class into another package.
 */
class Handler : HttpHandler {
    override fun invoke(request: Request): Response = Response(OK).body("Hello, world!")
}