package ca.fireball1725.coda45promadapter.controllers

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.netty.handler.codec.http.HttpHeaderNames
import io.prometheus.client.CollectorRegistry
import io.prometheus.client.exporter.common.TextFormat
import java.io.ByteArrayOutputStream
import java.io.OutputStreamWriter
import java.net.URLDecoder

@Controller("/metrics")
class PrometheusController(private val collectorRegistry: CollectorRegistry) {
    private fun parseQuery(query: String?): Set<String> {
        val names: MutableSet<String> = HashSet()
        if (query != null) {
            val pairs = query.split("&").toTypedArray()
            for (pair in pairs) {
                val idx = pair.indexOf("=")
                if (idx != -1 && URLDecoder.decode(pair.substring(0, idx), "UTF-8") == "name[]") {
                    names.add(URLDecoder.decode(pair.substring(idx + 1), "UTF-8"))
                }
            }
        }
        return names
    }

    @Get()
    fun serve(req: HttpRequest<*>): HttpResponse<*> {
        val os = ByteArrayOutputStream()
        val osw = OutputStreamWriter(os)
        val accept = req.headers[HttpHeaderNames.ACCEPT]
        val contentType = TextFormat.chooseContentType(accept)
        val query = req.uri.query
        TextFormat.writeFormat(contentType, osw, collectorRegistry.filteredMetricFamilySamples(parseQuery(query)))
        osw.flush()
        osw.close()
        val response = HttpResponse.ok(os.toString())
        response.headers.add("Content-Type", contentType)
        return response
    }
}
