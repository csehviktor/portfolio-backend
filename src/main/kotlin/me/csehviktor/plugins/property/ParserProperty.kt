package me.csehviktor.plugins.property

import io.ktor.client.statement.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import me.csehviktor.data.model.AccessToken
import me.csehviktor.data.model.CurrentTrackResponse

private val json: Json = Json {
    ignoreUnknownKeys = true
}

private inline fun <reified T> decode(string: String) = json.decodeFromString<T>(string)
private inline fun <reified T> encode(value: T) = json.encodeToJsonElement(value)

suspend fun HttpResponse.parseToken() = decode<AccessToken>(this.bodyAsText())
suspend fun HttpResponse.parseTrack() = decode<CurrentTrackResponse>(this.bodyAsText())
fun CurrentTrackResponse.parseToJson() = encode<CurrentTrackResponse>(this)