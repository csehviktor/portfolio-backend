package me.csehviktor

import io.github.cdimascio.dotenv.dotenv
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import me.csehviktor.data.model.CurrentTrackResponse

object Constants {
    object Environmental {
        var CLIENT_ID: String = ""
        var CLIENT_SECRET: String = ""
        var REFRESH_TOKEN: String = ""
    }
    var ENV = dotenv {
        directory = "./"
    }
    var CLIENT: HttpClient = null ?: HttpClient(CIO)
    var ACCESS_TOKEN: String? = null
    var CURRENT_TRACK: CurrentTrackResponse = CurrentTrackResponse(null, isPlaying = false)
}