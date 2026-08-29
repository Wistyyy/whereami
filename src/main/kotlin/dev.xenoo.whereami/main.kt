package dev.xenoo.whereami

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@Serializable
@JsonIgnoreUnknownKeys
data class response(
    val status: String,
    val country: String,
    val city: String
    )


fun main(args: Array<String>) {
    val client = HttpClient.newHttpClient()

    val request = HttpRequest.newBuilder()
        .uri(URI.create("http://ip-api.com/json/"))
        .GET()
        .build()

    val response = client.send(request, HttpResponse.BodyHandlers.ofString())

    if (response.statusCode() != 200) {
        println("Error: ${response.statusCode()}")
        return
    }

    val res: String = response.body()
    val output = jsonParser(res)
    argManager(args, output)
}

fun jsonParser(res: String): response {

    return Json.decodeFromString<response>(res)
}

fun argManager(args: Array<String>, output: response) {
    var i = 0
    while (i < args.size) {
        when (args[i]) {
            "--city" -> println("City: ${output.city}")
            "--country" -> println("Country: ${output.country}")
            "--status" -> println("Status: ${output.status}")
            "--all" -> println("Output:${output.status} \n City: ${output.city} \n Country: ${output.country}")
            else -> println("No valid argument detected")
        }
        i++
    }
}