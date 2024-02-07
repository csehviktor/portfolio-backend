package me.csehviktor.data.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.csehviktor.data.Request

@Serializable
data class ExternalUrl(
    val spotify: String
)

@Serializable
data class CurrentTrack(
    val name: String,

    @Contextual
    @SerialName("external_urls")
    val externalUrls: ExternalUrl
)

@Serializable
data class CurrentTrackResponse(
    @SerialName("item") val item: CurrentTrack?,
    @SerialName("is_playing") val isPlaying: Boolean,
)

data class CurrentTrackRequest(
    @SerialName("Authorization") val authorization: String
): Request

