package me.csehviktor.data

import io.ktor.client.request.*
import me.csehviktor.Constants.ACCESS_TOKEN
import me.csehviktor.Constants.CLIENT
import me.csehviktor.Constants.CURRENT_TRACK
import me.csehviktor.data.model.CurrentTrackRequest
import me.csehviktor.data.model.CurrentTrackResponse
import me.csehviktor.plugins.property.parseTrack

class Track {
    suspend fun getCurrentTrack(): CurrentTrackResponse? {
        val accessToken = ACCESS_TOKEN

        if(accessToken !== null) {
            val response: CurrentTrackResponse = CLIENT.get("https://api.spotify.com/v1/me/player/currently-playing") {
                headers {
                    CurrentTrackRequest(
                        "Bearer $accessToken"
                    ).build(this)
                }
            }.parseTrack()

            CURRENT_TRACK = CurrentTrackResponse(response.item, response.isPlaying)

            return CURRENT_TRACK
        }
        return null
    }

}