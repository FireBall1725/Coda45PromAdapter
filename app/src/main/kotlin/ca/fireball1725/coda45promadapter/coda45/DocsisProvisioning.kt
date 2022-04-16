package ca.fireball1725.coda45promadapter.coda45

import ca.fireball1725.coda45promadapter.metrics.Metrics
import ca.fireball1725.coda45promadapter.models.ModelDocsisProvisioning
import ca.fireball1725.coda45promadapter.util.APIProcessor
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class DocsisProvisioning {
    private val metrics = Metrics.create()

    fun process() {
        // nothing to process ...
    }

    private fun getDocsisProvisioning(): ModelDocsisProvisioning? {
        val statusLogResponse = APIProcessor.callApi(listOf("data", "getCMInit.asp"), "GET") ?: return null

        if (statusLogResponse.code != 200)
            return null

        val jsonResponse = statusLogResponse.body!!.string()

        return try {
            val type: Type = object : TypeToken<List<ModelDocsisProvisioning?>?>() {}.getType()
            val result: List<ModelDocsisProvisioning> = Gson().fromJson(jsonResponse, type)
            if (result.isNotEmpty())
                return result[0]
            return null
        } catch (e: java.lang.Exception) {
            // handle exception
            null
        }
    }
}
