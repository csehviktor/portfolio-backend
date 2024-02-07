package me.csehviktor

import io.ktor.server.application.*
import io.ktor.server.netty.*
import kotlinx.coroutines.runBlocking
import me.csehviktor.Constants.ENV
import me.csehviktor.plugins.config.configureRouting
import me.csehviktor.plugins.config.configureSerialization
import me.csehviktor.plugins.config.configureSockets

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() = runBlocking {
    Constants.Environmental.CLIENT_ID = ENV["CLIENT_ID"]
    Constants.Environmental.CLIENT_SECRET = ENV["CLIENT_SECRET"]
    Constants.Environmental.REFRESH_TOKEN = ENV["REFRESH_TOKEN"]

    configureSerialization()
    configureSockets()
    configureRouting()
}
