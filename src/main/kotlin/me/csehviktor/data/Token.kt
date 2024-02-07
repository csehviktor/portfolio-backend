package me.csehviktor.data

import io.ktor.client.*
import io.ktor.client.request.forms.*
import kotlinx.coroutines.runBlocking
import me.csehviktor.Constants.ACCESS_TOKEN
import me.csehviktor.Constants.Environmental.CLIENT_ID
import me.csehviktor.Constants.Environmental.CLIENT_SECRET
import me.csehviktor.Constants.Environmental.REFRESH_TOKEN
import me.csehviktor.data.model.AccessToken
import me.csehviktor.data.model.AccessTokenRequest
import me.csehviktor.plugins.property.allStringsValid
import me.csehviktor.plugins.property.parseToken

class Token {
    fun buildToken(client: HttpClient): Unit = runBlocking {
        if(allStringsValid(CLIENT_ID, CLIENT_SECRET, REFRESH_TOKEN)) {
            val response: AccessToken = client.submitForm(
                url = "https://accounts.spotify.com/api/token",
                formParameters = AccessTokenRequest(
                    CLIENT_ID,
                    CLIENT_SECRET,
                    REFRESH_TOKEN
                ).build()
            ).parseToken()

            ACCESS_TOKEN = response.accessToken
        }
    }
}