package ca.fireball1725.coda45promadapter.util

import ca.fireball1725.coda45promadapter.metrics.Metrics
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.util.concurrent.TimeUnit

object APIProcessor {
    private val metrics = Metrics.create()

    fun callApi(pathSegments: List<String>, httpMethod: String, messageBody: RequestBody? = null): Response? {
        // Build the web client
        val client = OkHttpClient().newBuilder()
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            .readTimeout(1000, TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(true)
            .build()

        // Build the URL
        var apiUrl = "http://192.168.100.1" // todo: Replace with config (base url)
        pathSegments.forEach {
            apiUrl += "/$it"
        }

        // Build the web request
        val request = Request.Builder()
            .url(apiUrl)
            .method(httpMethod, messageBody)
            .header("User-Agent", "Scraper")
            .addHeader("Content-Type", "application/json")
            .build()

        val timer = metrics.api_response_time(apiUrl).startTimer()

        // Make the API Call
        val response: Response
        try {
            response = client.newCall(request)
                .execute()
        } catch (e: java.lang.Exception) {
            // todo: handle failure
            return null
        }

        timer.observeDuration()

        if (!response.isSuccessful) {
            // todo: handle failure
            return null
        }

        return response
    }
}
