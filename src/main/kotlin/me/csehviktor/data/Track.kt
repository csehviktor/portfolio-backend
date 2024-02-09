package me.csehviktor.data

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import me.csehviktor.Constants.ACCESS_TOKEN
import me.csehviktor.Constants.CLIENT
import me.csehviktor.Constants.CURRENT_TRACK
import me.csehviktor.data.model.CurrentTrackRequest
import me.csehviktor.data.model.CurrentTrackResponse
import me.csehviktor.plugins.utils.parseTrack

class Track {
    suspend fun getCurrentTrack(): CurrentTrackResponse? {
        val accessToken = ACCESS_TOKEN

        if(accessToken !== null) {
            val response: HttpResponse = CLIENT.get("https://api.spotify.com/v1/me/player/currently-playing") {
                headers {
                    CurrentTrackRequest(
                        "Bearer $accessToken"
                    ).build(this)
                }
            }

            CURRENT_TRACK = CurrentTrackResponse(null, false)

            if(response.status != HttpStatusCode.NoContent) {
                val parsedResponse = response.parseTrack()

                CURRENT_TRACK = CurrentTrackResponse(
                    parsedResponse.item,
                    parsedResponse.isPlaying
                )
            }

            return CURRENT_TRACK
        }
        return null
    }

}