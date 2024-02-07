package me.csehviktor.plugins.config

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import me.csehviktor.Connection
import java.util.*
import kotlin.collections.LinkedHashSet

fun Application.configureRouting() {
    routing {
        val connections = Collections.synchronizedSet<Connection?>(LinkedHashSet())

        webSocket("/") {
            val thisConnection = Connection(this)
            connections += thisConnection

            try {
                for (frame in incoming) {
                    connections.forEach {
                        /*
                            trackInterval: 3 seconds = 3000L
                            tokenInterval: 1 hour = 3600000L
                         */
                        it.renewTrack(3000L, 3600000L)
                    }
                }
            } catch (e: Exception) {
                println(e.localizedMessage)
            } finally {
                connections -= thisConnection
            }
        }
    }
}
