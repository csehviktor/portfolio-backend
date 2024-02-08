package me.csehviktor

import io.ktor.util.date.*
import io.ktor.websocket.*
import kotlinx.coroutines.*
import me.csehviktor.Constants.CLIENT
import me.csehviktor.Constants.CURRENT_TRACK
import me.csehviktor.data.Token
import me.csehviktor.data.Track
import me.csehviktor.data.model.CurrentTrackResponse
import me.csehviktor.plugins.utils.parseToJson

class Connection(private val session: DefaultWebSocketSession) {
    private var trackResponse: CurrentTrackResponse = CURRENT_TRACK
    private var lastTrack: CurrentTrackResponse = trackResponse
    private var lastRequest: Long = 0
    private var mounted: Boolean = false

    private fun renewAccessToken(interval: Long) {
        val now: Long = getTimeMillis()

        if((now - lastRequest) >= interval) {
            Token().buildToken(CLIENT)

            lastRequest = now
            println("Renewed access token")
        }
    }

    suspend fun renewTrack(trackInterval: Long, tokenInterval: Long): Job {
        return CoroutineScope(Dispatchers.Default).launch {
            while(session.isActive) {
                renewAccessToken(tokenInterval)

                trackResponse = Track().getCurrentTrack()!!

                // reducing emissions
                if(!mounted || lastTrack != trackResponse) {
                    lastTrack = trackResponse
                    mounted = true

                    session.send("${ lastTrack.parseToJson() }")
                }

                // setting interval to X milliseconds
                delay(trackInterval)
            }
        }
    }
}