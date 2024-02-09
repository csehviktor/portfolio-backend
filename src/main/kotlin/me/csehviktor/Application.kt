package me.csehviktor

import io.ktor.server.application.*
import io.ktor.server.netty.*
import kotlinx.coroutines.runBlocking
import me.csehviktor.plugins.config.configureRouting
import me.csehviktor.plugins.config.configureSerialization
import me.csehviktor.plugins.config.configureSockets

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() = runBlocking {
    Constants.Environmental.CLIENT_ID = System.getenv("CLIENT_ID")
    Constants.Environmental.CLIENT_SECRET = System.getenv("CLIENT_SECRET")
    Constants.Environmental.REFRESH_TOKEN = System.getenv("REFRESH_TOKEN")

    configureSerialization()
    configureSockets()
    configureRouting()
}
