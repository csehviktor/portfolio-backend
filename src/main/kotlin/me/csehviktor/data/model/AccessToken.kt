package me.csehviktor.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.csehviktor.data.Request

@Serializable
data class AccessToken(
    @SerialName("access_token") val accessToken: String,
)

data class AccessTokenRequest (
    @SerialName("client_id") val clientId: String,
    @SerialName("client_secret") val clientSecret: String,
    @SerialName("refresh_token") val refreshToken: String,
    @SerialName("grant_type") val grantType: String = "refresh_token"
): Request