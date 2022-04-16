package ca.fireball1725.coda45promadapter.coda45

import ca.fireball1725.coda45promadapter.models.ModelDocsisEvent
import ca.fireball1725.coda45promadapter.util.APIProcessor
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.slf4j.LoggerFactory
import java.lang.reflect.Type

class DocsisEvent {
    private var lastLogEntry: ModelDocsisEvent? = null
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun process() {
        val result = getDocsisEvents()

        // Check to see if the list is null or empty, if so return
        if (result == null || result.isEmpty())
            return

        // Convert the results to a mutable list
        val docsisEvents: MutableList<ModelDocsisEvent> = result.toMutableList()

        // If the last log entry is null, then set the latest log event to it
        if (lastLogEntry == null) {
            lastLogEntry = docsisEvents[0]
        }

        // Delete entries in the log that we have already outputted
        for (i in docsisEvents.size - 1 downTo 0) {
            var it = docsisEvents[i]
            docsisEvents.removeAt(i)
            if (it == lastLogEntry)
                break
        }

        // Output the log to stdout
        for (i in docsisEvents.size - 1 downTo 0) {
            var it = docsisEvents[i]
            var logMessage = "datetime=\"${it.time}\" type=docsis-event msg=\"${it.event}\""
            when (it.priority) {
                "error" -> logger.error(logMessage)
                "critical" -> logger.warn(logMessage)
                else -> logger.info(logMessage)
            }
        }

        // Update the latest log entry
        if (docsisEvents.isNotEmpty())
            lastLogEntry = docsisEvents[0]
    }

    private fun getDocsisEvents(): List<ModelDocsisEvent>? {
        val statusLogResponse = APIProcessor.callApi(listOf("data", "status_log.asp"), "GET") ?: return null

        if (statusLogResponse.code != 200)
            return null

        val jsonResponse = statusLogResponse.body!!.string()

        return try {
            val type: Type = object : TypeToken<List<ModelDocsisEvent?>?>() {}.getType()
            Gson().fromJson(jsonResponse, type)
        } catch (e: java.lang.Exception) {
            // handle exception
            null
        }
    }
}
